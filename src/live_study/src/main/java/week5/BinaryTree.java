package week5;

import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;

public class BinaryTree {

    public static String bfs(Node root) {
        Queue<Node> q = new LinkedList<>();
        String result = "";
        LinkedList<Node> visited = new LinkedList<>();
        q.add(root);

        while (!q.isEmpty()) {
            Node node = q.poll();
            if(visited.contains(node)) {
                continue;
            }
            result += node.getValue();
            if (!Objects.isNull(node.getLeft())) {
                q.add(node.getLeft());
            }
            if (!Objects.isNull(node.getRight())) {
                q.add(node.getRight());
            }
        }

        return result;
    }

    public static String dfs(Node root) {
        if (Objects.isNull(root)) {
            throw new NullPointerException("root가 null입니다.");
        }
        return dfs(root, "");
    }

    private static String dfs(Node root, String result) {
        if (Objects.isNull(root)) {
            return result;
        }
        result = dfs(root.getLeft(), result);
        result += root.getValue();
        result = dfs(root.getRight(), result);

        return result;
    }
}
