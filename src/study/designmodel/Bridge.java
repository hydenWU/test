package study.designmodel;

//桥接模式： 如果系统中某个类存在两个独立变化的维度，通过桥接模式将这两个维度分离出来，使两者可以独立扩展。
/*
某软件系统要求能够显示 JPG、GIF、PNG 等多种格式图片，且能够在 Windows、Linux 等多个操作系统上运行。
系统首先将各种格式文件转换为像素矩阵，在不同的操作系统中调用不同的绘制函数来绘制像素矩阵。
系统需要较好的扩展性，以便在将来支持更多的图片格式和操作系统。
这个例子就将图片格式与操作系统这两个独立变化的维度分离。如果这两个成员变量都耦合在一个类中，
那么要实现上述的扩展则需要 3 * 2 = 6 个子类来实现，而分离出来独立变化则只需要 3 + 2 = 5 个额外的类，
当然变化的维度越多，桥接模式的效果则越显著
 */
/*
应用场景：编写功能类，要求能读本地或远程URL文件，文件类型是文本文件或是图像文件。
 */
public class Bridge {

    public static void main(String[] args) {
        Image windowsImage = new WindowsImage();
        ParseImage parseImage = new PngParse();
        parseImage.setImage(windowsImage);
        parseImage.paint();
    }
}

interface Image {
    void paint(String metrix);
}
class WindowsImage implements Image {
    @Override
    public void paint(String metrix) {
        System.out.println("windows painting...");
    }
}
class IOSImage implements Image {
    @Override
    public void paint(String metrix) {
        System.out.println("Ios painting...");
    }
}

abstract class ParseImage {
    private Image image;

    public void setImage(Image image) {
        this.image = image;
    }
    public void paint() {
        image.paint(parseImage());
    }
    abstract public String  parseImage();
}

class PngParse extends ParseImage {
    @Override
    public String  parseImage() {
        return "png metrix";
    }
}
class JpgParse extends ParseImage {
    @Override
    public String  parseImage() {
        return "Jpg metrix";
    }
}
