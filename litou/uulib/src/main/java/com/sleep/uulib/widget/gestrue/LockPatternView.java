package com.sleep.uulib.widget.gestrue;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.util.AttributeSet;
import android.view.HapticFeedbackConstants;
import android.view.MotionEvent;
import android.view.View;

import com.sleep.commonlib.util.SizeUtils;
import com.sleep.uulib.R;

import java.util.ArrayList;
import java.util.List;

/**
 * @ahthor yexm
 * @create time 2018/3/22
 **/

public class LockPatternView extends View {

    private float movingX, movingY;
    private boolean isActionMove = false;
    private boolean isActionDown = false;//default action down is false
    private boolean isActionUp = true;//default action up is true
    private boolean isCanMove = true;//是否可以滑动，

    public void setCanMove(boolean canMove) {
        isCanMove = canMove;
    }

    private int width, height;
    private int cellRadius, cellInnerRadius;
    private int cellBoxWidth, cellBoxHeight;
    //in stealth mode (default is false)
    private boolean mInStealthMode = false;
    //haptic feed back (default is false)
    private boolean mEnableHapticFeedback = false;
    //延时时间
    private long delayTime = 1000L;
    private int offset = 40;
    private Paint defaultPaint, selectPaint, errorPaint;
    private int mlineColor, initDefaultColor;
    //触摸范围
    private int selectRadius;
    private int mlineErrorColor;
    private Cell[][] mCells = new Cell[3][3];
    private List<Cell> sCells = new ArrayList<Cell>();
    private OnPatternListener patterListener;
    private static final double CONSTANT_COS_30 = Math.cos(Math.toRadians(30));

    public LockPatternView(Context context) {
        this(context, null);
    }

    public LockPatternView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LockPatternView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.init();
    }

    //初始化
    private void init() {
        initColor();
        this.initCellSize();
        this.init9Cells();
        this.initPaints();
    }

    //初始化颜色
    private void initColor() {
        mlineColor = getContext().getResources().getColor(R.color.color_1ea0e5);
        mlineErrorColor = getContext().getResources().getColor(R.color.color_ff5353);
        initDefaultColor = getContext().getResources().getColor(R.color.color_969696);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //软编程
        setLayerType(LAYER_TYPE_SOFTWARE, null);
        this.drawToCanvas(canvas);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        getMeasuredHeight();
        this.width = getMeasuredWidth();
        this.height = getMeasuredHeight();
        if (width != height) {
            throw new IllegalArgumentException("the width must be equals height");
        }
        this.initCellSize();
        this.set9CellsSize();
        this.invalidate();
    }

    /**
     * draw the view to canvas
     *
     * @param canvas
     */
    private void drawToCanvas(Canvas canvas) {

        for (int i = 0; i < mCells.length; i++) {
            for (int j = 0; j < mCells[i].length; j++) {
                if (mCells[i][j].getStatus() == Cell.STATE_CHECK) {
                    selectPaint.setStyle(Style.STROKE);
                    selectPaint.setStyle(Style.FILL);
                    canvas.drawCircle(mCells[i][j].getX(), mCells[i][j].getY(),
                            this.cellInnerRadius / 2.4f, selectPaint);

                } else if (mCells[i][j].getStatus() == Cell.STATE_NORMAL) {
                    canvas.drawCircle(mCells[i][j].getX(), mCells[i][j].getY(),
                            this.cellInnerRadius / 2.4f, defaultPaint);
                } else if (mCells[i][j].getStatus() == Cell.STATE_CHECK_ERROR) {
                    errorPaint.setStyle(Style.FILL);
                    canvas.drawCircle(mCells[i][j].getX(), mCells[i][j].getY(),
                            this.cellInnerRadius / 2.4f, this.errorPaint);
                    errorPaint.setStyle(Style.FILL);
                    errorPaint.setColor(Color.WHITE);
                    errorPaint.setColor(mlineErrorColor);
                }
            }
        }

        if (sCells.size() > 0) {
            //temporary cell: at the beginning the cell is the first of sCells
            Cell tempCell = sCells.get(0);

            for (int i = 1; i < sCells.size(); i++) {
                Cell cell = sCells.get(i);
                if (cell.getStatus() == Cell.STATE_CHECK) {
                    selectPaint.setColor(mlineColor);
                    drawLine(tempCell, cell, canvas, selectPaint);

                } else if (cell.getStatus() == Cell.STATE_CHECK_ERROR) {
                    drawLine(tempCell, cell, canvas, errorPaint);
                }
                tempCell = cell;
            }

            if (isActionMove && !isActionUp) {
                this.drawLineFollowFinger(tempCell, canvas, selectPaint);
            }
        }
    }

    private void initCellSize() {
//        this.cellRadius = (int) ((this.width - offset * 2) / 4 / 2.2f);
        this.cellRadius = SizeUtils.dp2px(getContext(), 6);
        this.selectRadius = SizeUtils.dp2px(getContext(), 14);
        this.cellInnerRadius = SizeUtils.dp2px(getContext(), 18);
        this.cellBoxWidth = (this.width - offset * 2) / 3;
        this.cellBoxHeight = (this.height - offset * 2) / 3;
    }

    private void init9Cells() {
        int distance = this.cellBoxWidth + this.cellBoxWidth / 2 - this.cellRadius;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                mCells[i][j] = new Cell(distance * j + cellRadius + offset,
                        distance * i + cellRadius + offset, i, j, 3 * i + j + 1);
            }
        }
    }

    private void set9CellsSize() {
        int distance = this.cellBoxWidth + this.cellBoxWidth / 2 - this.cellRadius;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                mCells[i][j].setX(distance * j + cellRadius + offset);
                mCells[i][j].setY(distance * i + cellRadius + offset);
            }
        }
    }

    private void initPaints() {
        defaultPaint = new Paint();
        defaultPaint.setColor(initDefaultColor);
        defaultPaint.setStrokeWidth(4.0f);
        defaultPaint.setStyle(Style.STROKE);
        defaultPaint.setAntiAlias(true);

        selectPaint = new Paint();
        selectPaint.setColor(mlineColor);
        selectPaint.setStrokeWidth(4.0f);
        selectPaint.setAntiAlias(true);


        errorPaint = new Paint();
        errorPaint.setColor(mlineErrorColor);
        errorPaint.setStrokeWidth(4.0f);
        errorPaint.setAntiAlias(true);


    }

    /**
     * draw line not include circle (check whether the cell between two cells )
     *
     * @param preCell
     * @param nextCell
     * @param canvas
     * @param paint
     */
    private void drawLine(Cell preCell, Cell nextCell, Canvas canvas, Paint paint) {
        Cell centerCell = getCellBetweenTwoCells(preCell, nextCell);
        if (centerCell != null && sCells.contains(centerCell)) {
            drawLineNotIncludeCircle(centerCell, preCell, canvas, paint);
            drawLineNotIncludeCircle(centerCell, nextCell, canvas, paint);
        } else {
            drawLineNotIncludeCircle(preCell, nextCell, canvas, paint);
        }
    }

    /**
     * draw line not include circle (the line do not show inside the circle)
     *
     * @param preCell
     * @param nextCell
     * @param canvas
     * @param paint
     */
    private void drawLineNotIncludeCircle(Cell preCell, Cell nextCell, Canvas canvas, Paint paint) {
        float distance = LockPatternUtil.getDistanceBetweenTwoPoints(
                preCell.getX(), preCell.getY(), nextCell.getX(), nextCell.getY());
        float x1 = this.cellRadius / distance * (nextCell.getX() - preCell.getX()) + preCell.getX();
        float y1 = this.cellRadius / distance * (nextCell.getY() - preCell.getY()) + preCell.getY();
        float x2 = (distance - this.cellRadius) / distance *
                (nextCell.getX() - preCell.getX()) + preCell.getX();
        float y2 = (distance - this.cellRadius) / distance *
                (nextCell.getY() - preCell.getY()) + preCell.getY();
        canvas.drawLine(x1, y1, x2, y2, paint);
    }

    /**
     * get the cell between two cells (it has the limitation: the pattern must be 3x3)
     *
     * @param preCell  previous cell
     * @param nextCell next cell
     * @return Cell
     */
    private Cell getCellBetweenTwoCells(Cell preCell, Cell nextCell) {
        if (preCell.getRow() == nextCell.getRow()) {
            if (Math.abs(nextCell.getColumn() - preCell.getColumn()) > 1) {
                return mCells[preCell.getRow()][1];
            } else {
                return null;
            }
        } else if (preCell.getColumn() == nextCell.getColumn()) {
            if (Math.abs(nextCell.getRow() - preCell.getRow()) > 1) {
                return mCells[1][preCell.getColumn()];
            } else {
                return null;
            }
        } else if (Math.abs(nextCell.getColumn() - preCell.getColumn()) > 1
                && Math.abs(nextCell.getRow() - preCell.getRow()) > 1) {
            return mCells[1][1];
        } else {
            return null;
        }
    }

    /**
     * draw line follow finger
     * (do not draw line inside the selected cell,
     * but it is only the starting cell not the other's cell)
     *
     * @param preCell
     * @param canvas
     * @param paint
     */
    private void drawLineFollowFinger(Cell preCell, Canvas canvas, Paint paint) {
        float distance = LockPatternUtil.getDistanceBetweenTwoPoints(
                preCell.getX(), preCell.getY(), movingX, movingY);
        if (distance > this.cellRadius) {
            float x1 = this.cellRadius / distance * (movingX - preCell.getX()) + preCell.getX();
            float y1 = this.cellRadius / distance * (movingY - preCell.getY()) + preCell.getY();
            canvas.drawLine(x1, y1, movingX, movingY, paint);
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (isCanMove == false) {
            return super.onTouchEvent(event);
        }
        float ex = event.getX();
        float ey = event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                handleActionDown(ex, ey);
                break;
            case MotionEvent.ACTION_MOVE:
                handleActionMove(ex, ey);
                break;
            case MotionEvent.ACTION_UP:
                handleActionUp();
                break;
        }
        return true;
    }

    /**
     * handle action down
     *
     * @param ex
     * @param ey
     */
    private void handleActionDown(float ex, float ey) {

        isActionMove = false;
        isActionDown = true;
        isActionUp = false;

        this.setPattern(DisplayMode.DEFAULT);

        if (this.patterListener != null) {
            this.patterListener.onPatternStart();
        }

        Cell cell = checkSelectCell(ex, ey);
        if (cell != null) {
            addSelectedCell(cell);
        }
    }

    /**
     * handle action move
     *
     * @param ex
     * @param ey
     */
    private void handleActionMove(float ex, float ey) {
        isActionMove = true;
        movingX = ex;
        movingY = ey;
        Cell cell = checkSelectCell(ex, ey);
        if (cell != null) {
            addSelectedCell(cell);
        }
        this.setPattern(DisplayMode.NORMAL);
    }

    /**
     * handle action up
     */
    private void handleActionUp() {
        isActionMove = false;
        isActionUp = true;
        isActionDown = false;

        this.setPattern(DisplayMode.NORMAL);

        if (this.patterListener != null) {
            this.patterListener.onPatternComplete(sCells);
        }
    }

    /**
     * check user's touch moving is or not in the area of cells
     *
     * @param x
     * @param y
     * @return
     */
    private Cell checkSelectCell(float x, float y) {
        for (int i = 0; i < mCells.length; i++) {
            for (int j = 0; j < mCells[i].length; j++) {
                Cell cell = mCells[i][j];
                if (LockPatternUtil.checkInRound(cell.x, cell.y, selectRadius, x, y, 0)) {
                    return cell;
                }
            }
        }
        return null;
    }

    /**
     * add selected cell
     *
     * @param cell
     */
    private void addSelectedCell(Cell cell) {
        if (!sCells.contains(cell)) {
            cell.setStatus(Cell.STATE_CHECK);
            handleHapticFeedback();
            sCells.add(cell);
        }
        setPattern(DisplayMode.NORMAL);
    }

    /**
     * handle haptic feedback
     * (if mEnableHapticFeedback true: has haptic else not have haptic)
     */
    private void handleHapticFeedback() {
        if (mEnableHapticFeedback) {
            performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY,
                    HapticFeedbackConstants.FLAG_IGNORE_VIEW_SETTING |
                            HapticFeedbackConstants.FLAG_IGNORE_GLOBAL_SETTING);
        }
    }

    /**
     * @param value (ex: 2,1,3,6,4,5)
     * @return the selected cell
     */
    @Deprecated
    public List<Cell> setDefaultSelectedCell(String value) {
        String[] str = value.split(",");
        for (int i = 0; i < str.length; i++) {
            int val = Integer.valueOf(str[i]);
            if (val <= 3) {
                addSelectedCell(mCells[0][val - 1]);
            } else if (val <= 6) {
                addSelectedCell(mCells[1][val - 4]);
            } else {
                addSelectedCell(mCells[2][val - 7]);
            }
        }
        return sCells;
    }

    /**
     * the display mode of the pattern
     */
    public enum DisplayMode {
        //show default pattern (the default pattern is initialize status)
        DEFAULT,
        //show selected pattern normal
        NORMAL,
        //show selected pattern error
        ERROR;
    }

    /**
     * set pattern
     *
     * @param mode (details see the DisplayMode)
     */
    public void setPattern(DisplayMode mode) {
        switch (mode) {
            case DEFAULT:
                for (Cell cell : sCells) {
                    cell.setStatus(Cell.STATE_NORMAL);
                }
                sCells.clear();
                break;
            case NORMAL:
                break;
            case ERROR:
                isCanMove = false;
                for (Cell cell : sCells) {
                    cell.setStatus(Cell.STATE_CHECK_ERROR);
                }
                break;
        }
        this.handleStealthMode();
    }

    /**
     * handle the stealth mode (if true: do not post invalidate; false: post invalidate)
     */
    private void handleStealthMode() {
        if (!mInStealthMode) {
            this.postInvalidate();
        }
    }

    /**
     * remove the post delay clear pattern
     */
    public void removePostClearPatternRunnable() {
        this.removeCallbacks(mClearPatternRunnable);
    }

    /**
     * delay clear pattern
     *
     * @param delay the delay time (if delay less than 0, it will be 600L)
     */
    public void postClearPatternRunnable(long delay) {
       isCanMove=false;
        if (delay >= 0L) {
            delayTime = delay;
        }
        this.removeCallbacks(mClearPatternRunnable);
        this.postDelayed(mClearPatternRunnable, delayTime);

    }

    private Runnable mClearPatternRunnable = new Runnable() {
        @Override
        public void run() {
            LockPatternView.this.setPattern(DisplayMode.DEFAULT);
            isCanMove = true;
        }
    };

    /**
     * @return Whether the view is in stealth mode.
     */
    public boolean isInStealthMode() {
        return mInStealthMode;
    }

    /**
     * Set whether the view is in stealth mode.  If true, there will be no
     * visible feedback as the user enters the pattern.
     *
     * @param inStealthMode Whether in stealth mode.
     */
    public void setInStealthMode(boolean inStealthMode) {
        mInStealthMode = inStealthMode;
    }

    /**
     * @return Whether the view has tactile feedback enabled.
     */
    public boolean isTactileFeedbackEnabled() {
        return mEnableHapticFeedback;
    }

    /**
     * Set whether the view will use tactile feedback.  If true, there will be
     * tactile feedback as the user enters the pattern.
     *
     * @param tactileFeedbackEnabled Whether tactile feedback is enabled
     */
    public void setTactileFeedbackEnabled(boolean tactileFeedbackEnabled) {
        mEnableHapticFeedback = tactileFeedbackEnabled;
    }

    public void setOnPatternListener(OnPatternListener patternListener) {
        this.patterListener = patternListener;
    }

    /**
     * callback interface
     */
    public interface OnPatternListener {
        void onPatternStart();

        void onPatternComplete(List<Cell> cells);
    }

    public class Cell {

        private int x;// the x position of circle's center point
        private int y;// the y position of circle's center point
        private int row;// the cell in which row
        private int column;// the cell in which column
        private int index;// the cell value
        private int status = 0;//default status

        //default status
        public static final int STATE_NORMAL = 0;
        //checked status
        public static final int STATE_CHECK = 1;
        //checked error status
        public static final int STATE_CHECK_ERROR = 2;

        public Cell() {
        }

        public Cell(int x, int y, int row, int column, int index) {
            this.x = x;
            this.y = y;
            this.row = row;
            this.column = column;
            this.index = index;
        }

        public int getX() {
            return this.x;
        }

        public void setX(int x) {
            this.x = x;
        }

        public int getY() {
            return this.y;
        }

        public void setY(int y) {
            this.y = y;
        }

        public int getRow() {
            return this.row;
        }

        public int getColumn() {
            return this.column;
        }

        public int getIndex() {
            return this.index;
        }

        public int getStatus() {
            return this.status;
        }

        public void setStatus(int status) {
            this.status = status;
        }


        @Override
        public String toString() {
            return "Cell{" +
                    "column=" + column +
                    ", x=" + x +
                    ", y=" + y +
                    ", row=" + row +
                    ", index=" + index +
                    ", status=" + status +
                    '}';
        }
    }
}
