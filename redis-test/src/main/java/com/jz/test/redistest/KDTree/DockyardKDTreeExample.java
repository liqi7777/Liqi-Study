package com.jz.test.redistest.KDTree;

import java.util.ArrayList;
import java.util.List;


/**
 * @author liqi
 * create  2025/1/15 11:01 上午
 */

/**
 * ```
 * **代码解释**：
 * - **类 `Point`**：
 * - 用于表示一个二维空间中的点，包含 `x` 和 `y` 坐标。
 * <p>
 * - **类 `KDTreeNode`**：
 * - 表示 KDTree 的节点，包含一个 `Point` 对象、左右子节点指针和当前节点的分割维度。
 * <p>
 * - **类 `KDTree`**：
 * - `buildKDTree(List<Point> points, int depth)` 方法：
 * - 该方法用于构建 KDTree。根据深度 `depth` 交替在 `x` 和 `y` 维度上对 `points` 列表进行排序，找到中位数点创建节点，将小于中位数的点放入左子树，大于中位数的点放入右子树，递归构建左右子树。
 * - `distance(Point p1, Point p2)` 方法：
 * - 计算两个点之间的欧几里得距离，用于后续的最近邻搜索。
 * - `nearestNeighbor(KDTreeNode node, Point target, NearestNeighbor nearest)` 方法：
 * - 该方法用于在以 `node` 为根的子树中查找离 `target` 最近的点。它会先计算当前节点与目标点的距离，如果比已知最近邻更近，则更新最近邻。根据目标点和当前节点的分割维度判断优先搜索哪个子树，同时，如果目标点到分割超平面的距离小于当前最近邻距离，会继续搜索另一个子树。
 * - `nearestNeighbor(Point target)` 方法：
 * - 从根节点开始调用 `nearestNeighbor(KDTreeNode node, Point target, NearestNeighbor nearest)` 进行最近邻搜索。
 * <p>
 * - **类 `NearestNeighbor`**：
 * - 存储最近邻的 `Point` 及其与目标点的距离。
 * <p>
 * <p>
 * **使用说明**：
 * - 在 `main` 方法中，首先创建了一个包含多个箱区贝位坐标的 `List<Point>`。
 * - 使用这些坐标构建了一个 `KDTree` 实例。
 * - 然后创建了一个表示车辆位置的 `Point` 对象。
 * - 调用 `kdTree.nearestNeighbor(vehiclePosition)` 方法找到离车辆位置最近的箱区贝位，并将结果存储在 `nearest` 中。
 * - 最后打印出最近箱区贝位的坐标和与车辆的距离。
 * <p>
 * <p>
 * 这个示例代码展示了如何使用 KDTree 实现最近邻搜索，可用于在码头作业场景中找到与车辆当前位置最近的箱区贝位。根据实际需求，你可以扩展该代码，比如从数据库中加载更多的箱区贝位信息，处理更多的查询，或者优化 KDTree 的构建和搜索算法。
 */
class Point {
    double x;
    double y;

    Point(double x, double y) {
        this.x = x;
        this.y = y;
    }
}

class KDTreeNode {
    Point point;
    KDTreeNode left;
    KDTreeNode right;
    int splitDim;

    KDTreeNode(Point point, int splitDim) {
        this.point = point;
        this.splitDim = splitDim;
        this.left = null;
        this.right = null;
    }
}

class KDTree {
    KDTreeNode root;

    KDTree(List<Point> points) {
        root = buildKDTree(points, 0);
    }

    private KDTreeNode buildKDTree(List<Point> points, int depth) {
        if (points.isEmpty()) {
            return null;
        }
        int splitDim = depth % 2; // 交替在 x 和 y 维度上划分
        points.sort((p1, p2) -> {
            if (splitDim == 0) {
                return Double.compare(p1.x, p2.x);
            } else {
                return Double.compare(p1.y, p2.y);
            }
        });
        int medianIndex = points.size() / 2;
        KDTreeNode node = new KDTreeNode(points.get(medianIndex), splitDim);
        List<Point> leftPoints = new ArrayList<>(points.subList(0, medianIndex));
        List<Point> rightPoints = new ArrayList<>(points.subList(medianIndex + 1, points.size()));
        node.left = buildKDTree(leftPoints, depth + 1);
        node.right = buildKDTree(rightPoints, depth + 1);
        return node;
    }

    private double distance(Point p1, Point p2) {
        double dx = p1.x - p2.x;
        double dy = p1.y - p2.y;
        return Math.sqrt(dx * dx + dy * dy);
    }

    private NearestNeighbor nearestNeighbor(KDTreeNode node, Point target, NearestNeighbor nearest) {
        if (node == null) {
            return nearest;
        }
        double dist = distance(node.point, target);
        if (nearest == null || dist < nearest.distance) {
            nearest = new NearestNeighbor(node.point, dist);
        }
        int splitDim = node.splitDim;
        KDTreeNode firstChild, secondChild;
        if ((splitDim == 0 && target.x < node.point.x) || (splitDim == 1 && target.y < node.point.y)) {
            firstChild = node.left;
            secondChild = node.right;
        } else {
            firstChild = node.right;
            secondChild = node.left;
        }
        nearest = nearestNeighbor(firstChild, target, nearest);
        double splitDist;
        if (splitDim == 0) {
            splitDist = Math.abs(target.x - node.point.x);
        } else {
            splitDist = Math.abs(target.y - node.point.y);
        }
        if (splitDist < nearest.distance) {
            nearest = nearestNeighbor(secondChild, target, nearest);
        }
        return nearest;
    }

    NearestNeighbor nearestNeighbor(Point target) {
        return nearestNeighbor(root, target, null);
    }
}

class NearestNeighbor {
    Point point;
    double distance;

    NearestNeighbor(Point point, double distance) {
        this.point = point;
        this.distance = distance;
    }
}

public class DockyardKDTreeExample {
    public static void main(String[] args) {
        // 假设这是箱区贝位的坐标
        List<Point> bayPositions = new ArrayList<>();
        bayPositions.add(new Point(1.0, 2.0));
        bayPositions.add(new Point(3.0, 4.0));
        bayPositions.add(new Point(5.0, 6.0));
        bayPositions.add(new Point(7.0, 8.0));
        bayPositions.add(new Point(9.0, 10.0));

        KDTree kdTree = new KDTree(bayPositions);

        // 假设这是车辆的当前位置
        Point vehiclePosition = new Point(2.5, 3.5);

        NearestNeighbor nearest = kdTree.nearestNeighbor(vehiclePosition);
        System.out.println("Nearest Bay Position: (" + nearest.point.x + ", " + nearest.point.y + ")");
        System.out.println("Distance: " + nearest.distance);
    }
}
