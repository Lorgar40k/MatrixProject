import java.util.*;
import java.util.stream.*;

public class MatrixDiagonalMinStream {
    public static void main(String[] args) {
        int[][] matrix = {
                {1, 2, 3, 4, 5},
                {5, 7, 9, 2, 1},
                {0, 9, 1, 8, 7},
                {6, 3, 6, 6, 6},
                {99, 100, -2, 3, 1}
        };

        int n = matrix.length;

        OptionalInt min = IntStream.range(0, n)
                .boxed()
                .flatMap(i -> IntStream.range(0, n)
                        .mapToObj(j -> new int[]{i, j}))
                .filter(pos -> {
                    int i = pos[0], j = pos[1];
                    boolean isMain = i == j;
                    boolean isSide = i + j == n - 1;
                    return (isMain || isSide) && !(isMain && isSide);
                })
                .mapToInt(pos -> matrix[pos[0]][pos[1]])
                .min();

        System.out.println("Минимальный элемент диагоналей (без центра): " +
                min.orElseThrow(() -> new RuntimeException("Нет элементов")));
    }
}
