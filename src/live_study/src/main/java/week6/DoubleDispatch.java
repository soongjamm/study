package week6;

import java.util.Arrays;
import java.util.List;

public class DoubleDispatch {
    interface Post {
        void postOn(SNS sns);
    }

    static class Text implements Post {

        @Override
        public void postOn(SNS sns) {
            sns.post(this); // 두번째 다이나믹 메소드 디스패치
        }

    }

    static class Picture implements Post {

        @Override
        public void postOn(SNS sns) {
            sns.post(this); // 두번째 다이나믹 메소드 디스패치
        }
    }

    interface SNS {
        void post(Text post);
        void post(Picture post);
    }

    static class Facebook implements SNS {

        @Override
        public void post(Text text) {
            System.out.println("text -> Facebook");
        }

        @Override
        public void post(Picture picture) {
            System.out.println("picture -> Facebook");
        }
    }

    static class Instagram implements SNS {
        @Override
        public void post(Text text) {
            System.out.println("text -> Instagram");
        }

        @Override
        public void post(Picture picture) {
            System.out.println("picture -> Instagram");
        }
    }


    public static void main(String[] args) {
        List<Post> post = Arrays.asList(new Picture(), new Text());
        List<SNS> sns = Arrays.asList(new Facebook(), new Instagram());

        post.forEach(p -> sns.forEach(s -> p.postOn(s))); // 첫번째 다이나믹 메소드 디스패치
    }
}
