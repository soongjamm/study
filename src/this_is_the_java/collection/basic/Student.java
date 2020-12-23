package this_is_the_java.collection.basic;

public class Student {
    public int sno;
    public String name;

    public Student(int sno, String name) {
        this.sno = sno;
        this.name = name;
    }

    public boolean equals(Object obj) {
        if (obj instanceof Student) {
            Student student = (Student) obj;
            return (sno == student.sno) && (name == student.name);
        } else {
            return false;
        }
    }

    public int hashCode() {
        return sno + name.hashCode(); // 학번과 이름이 같다면 동일한 값을 리턴
    }
}
