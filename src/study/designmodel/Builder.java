package study.designmodel;

//创建者模式：StringBuilder.append
//构造参数过多时，可以用该模式，创建的更加清晰
/*
建造者模式也称为生成器模式，是一种对象创建型模式，它可以将复杂对象的建造过程抽象出来（抽象类别），使这个抽象过程的不同实现方法可以构造出不同表现（属性）的对象。
  建造者模式意在为重叠构造器这种反模式(telescoping constructor anti-pattern)找到一种解决方案，对于重叠构造器反模式，我们经常能看到类似于下列的构造器形式（下述例子来源于《Effective Java》）：
 */
public class Builder {
    public static void main(String[] args) {

        Staff staff = new Staff.Builder("210211").Telephone("143122222").Email("qq@125.com").build();
        System.out.println(staff);
    }
}

class Staff {
    private final String sfz;//必要参数
    private final String tel;
    private final String email;
    private final String addr;

    private Staff(Builder inner) {
        this.sfz = inner.sfz;
        this.tel = inner.tel;
        this.email = inner.email;
        this.addr = inner.addr;
    }

    @Override
    public String toString() {
        return "Staff{" +
                "sfz='" + sfz + '\'' +
                ", tel='" + tel + '\'' +
                ", email='" + email + '\'' +
                ", addr='" + addr + '\'' +
                '}';
    }

    static class Builder {
        //必要参数
        private   String sfz;
        //可选参数
        private   String tel="";
        private   String email="";
        private   String addr="";

        public Builder(String sfz) {
            this.sfz = sfz;
        }
        public Builder Telephone(String tel){
            this.tel = tel;
            return this;
        }
        public Builder Email(String email){
            this.email = email;
            return this;
        }
        public Builder Address(String addr){
            this.addr = addr;
            return this;
        }

        public Staff build () {
            return new Staff(this);
        }
    }

}
