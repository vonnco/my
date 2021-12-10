package com.vonco.zxing.util;

import com.google.zxing.*;
import com.google.zxing.common.BitArray;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.datamatrix.encoder.SymbolShapeHint;
import com.google.zxing.pdf417.encoder.Compaction;
import com.google.zxing.pdf417.encoder.Dimensions;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.sun.jndi.toolkit.url.Uri;
import com.sun.org.apache.xml.internal.security.exceptions.Base64DecodingException;
import com.sun.xml.internal.messaging.saaj.util.ByteOutputStream;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.EnumMap;
import java.util.Locale;
import java.util.Map;

/**
 * @author ke feng
 * @title: QRCodeUtil
 * @projectName my
 * @description: TODO
 * @date 2021/11/3 11:57
 */
public class QRCodeUtil {

    private static final String CHARSET = "UTF-8";//编码类型

    private static final BarcodeFormat BARCODE_FORMAT = BarcodeFormat.QR_CODE;//二维码

    private static final String IMAGE_FORMAT = "png";//生成二维码格式

    private static final String BASE64_HEAD = "data:image/png;base64,";//base64头

    private static final int WIDTH = 300;//二维码宽度

    private static final int HEIGHT = 300;//二维码高度

    private static final int LOGO_WIDTH = 80;//logo高度

    private static final int LOGO_HEIGHT = 80;//logo高度

    public static String encode(String content) {
        return encode(content, null, WIDTH, HEIGHT);
    }

    public static String encode(String content,String logoUrl){
        return encode(content,logoUrl,WIDTH,HEIGHT);
    }

    public static String encode(String content,String logoUrl,int width, int height) {
        ByteOutputStream outputStream = new ByteOutputStream();
        Map<EncodeHintType, Object> hints = new EnumMap<>(EncodeHintType.class);
        //编码类型
        hints.put(EncodeHintType.CHARACTER_SET, CHARSET);
        //纠错等级(L < M < Q < H  纠错能力越高，可存储的越少)
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
        //边距
        hints.put(EncodeHintType.MARGIN, 1);
        try {
            BitMatrix matrix = new MultiFormatWriter().encode(content, BARCODE_FORMAT, width, height, hints);
            BufferedImage bufferedImage = new BufferedImage(matrix.getWidth(), matrix.getHeight(), BufferedImage.TYPE_INT_RGB);
            for (int x = 0; x < width; x++) {
                for (int y = 0; y < height; y++) {
                    bufferedImage.setRGB(x, y, matrix.get(x, y) ? 0xFF000000 : 0xFFFFFFFF);
                }
            }
            if (StringUtils.isNotBlank(logoUrl)) {
                insertImage(bufferedImage,logoUrl,width,height);
            }
            ImageIO.write(bufferedImage, IMAGE_FORMAT, outputStream);
            ImageIO.write(bufferedImage, IMAGE_FORMAT, new File("d://test.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return BASE64_HEAD + Base64.encodeBase64String(outputStream.getBytes());
    }

    public static String decode(File file) {
        try {
            BufferedImage image = ImageIO.read(file);
            if (image == null) {
                return null;
            }
            BufferedImageLuminanceSource source = new BufferedImageLuminanceSource(image);
            BinaryBitmap binaryBitmap = new BinaryBitmap(new HybridBinarizer(source));
            Map<DecodeHintType, Object> hints = new EnumMap<>(DecodeHintType.class);
            //编码类型
            hints.put(DecodeHintType.CHARACTER_SET, CHARSET);
            Result result = new MultiFormatReader().decode(binaryBitmap, hints);
            return result.getText();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private static void insertImage(BufferedImage source, String logoUrl,int width,int height) throws Exception {
        Image src = ImageIO.read(new URL(logoUrl));
        int logoWidth = src.getWidth(null);
        int logoHeight = src.getHeight(null);
        // 如果图片太大压缩LOGO
        if (logoWidth > LOGO_WIDTH) {
            logoWidth = LOGO_WIDTH;
        }
        if (logoHeight > LOGO_HEIGHT) {
            logoHeight = LOGO_HEIGHT;
        }
        Image image = src.getScaledInstance(logoWidth, logoWidth, Image.SCALE_SMOOTH);
        BufferedImage tag = new BufferedImage(logoWidth, logoWidth, BufferedImage.TYPE_INT_RGB);
        Graphics g = tag.getGraphics();
        g.drawImage(image, 0, 0, null); // 绘制缩小后的图
        g.dispose();
        src = image;
        // 插入LOGO
        Graphics2D graph = source.createGraphics();
        int x = (width - logoWidth) / 2;
        int y = (height - logoHeight) / 2;
        graph.drawImage(src, x, y, logoWidth, logoHeight, null);
        Shape shape = new RoundRectangle2D.Float(x, y, logoWidth, logoHeight, 6, 6);
        graph.setStroke(new BasicStroke(3f));
        graph.draw(shape);
        graph.dispose();
    }
}
