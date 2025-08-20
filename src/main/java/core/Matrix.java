package core;

import java.util.function.Function;

public class Matrix {
    public final int rows;
    public final int cols;
    public final double[][] data;

    public Matrix(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        this.data = new double[rows][cols];
    }

    public static Matrix fromArray(double[] array) {
        Matrix result = new Matrix(array.length, 1);
        for (int i = 0; i < array.length; i++) {
            result.data[i][0] = array[i];
        }
        return result;
    }

    public double[] toArray() {
        double[] array = new double[rows * cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                array[i * cols + j] = data[i][j];
            }
        }
        return array;
    }

    public void randomize() {
        for (int i = 0; i < rows; i++)
            for (int j = 0; j < cols; j++)
                data[i][j] = Math.random() * 2 - 1; // Random between -1 and 1
    }

    public Matrix add(Matrix other) {
        Matrix result = new Matrix(rows, cols);
        for (int i = 0; i < rows; i++)
            for (int j = 0; j < cols; j++)
                result.data[i][j] = this.data[i][j] + other.data[i][j];
        return result;
    }

    public Matrix subtract(Matrix other) {
        Matrix result = new Matrix(rows, cols);
        for (int i = 0; i < rows; i++)
            for (int j = 0; j < cols; j++)
                result.data[i][j] = this.data[i][j] - other.data[i][j];
        return result;
    }

    public Matrix dot(Matrix other) {
        Matrix result = new Matrix(this.rows, other.cols);
        for (int i = 0; i < result.rows; i++)
            for (int j = 0; j < result.cols; j++)
                for (int k = 0; k < this.cols; k++)
                    result.data[i][j] += this.data[i][k] * other.data[k][j];
        return result;
    }

    public Matrix transpose() {
        Matrix result = new Matrix(cols, rows);
        for (int i = 0; i < rows; i++)
            for (int j = 0; j < cols; j++)
                result.data[j][i] = this.data[i][j];
        return result;
    }

    public Matrix apply(Function<Double, Double> func) {
        Matrix result = new Matrix(rows, cols);
        for (int i = 0; i < rows; i++)
            for (int j = 0; j < cols; j++)
                result.data[i][j] = func.apply(this.data[i][j]);
        return result;
    }

    public static Matrix multiplyElementWise(Matrix a, Matrix b) {
        Matrix result = new Matrix(a.rows, a.cols);
        for (int i = 0; i < a.rows; i++)
            for (int j = 0; j < a.cols; j++)
                result.data[i][j] = a.data[i][j] * b.data[i][j];
        return result;
    }
    public Matrix multiplyByScalar(double scalar) {
        Matrix result = new Matrix(rows, cols);
        for (int i = 0; i < rows; i++)
            for (int j = 0; j < cols; j++)
                result.data[i][j] = this.data[i][j] * scalar;
        return result;
    }

}
