package com.briup.restaurant.util;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.Binarizer;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.EncodeHintType;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;

/**
 * 二维码工具类
 * @author yanzc
 *
 */
public class QRCodeUtil {

    private static final Logger logger = LoggerFactory.getLogger(QRCodeUtil.class);

    //二维码颜色
    private static final int BLACK = 0xFF000000;
    //二维码颜色
    private static final int WHITE = 0xFFFFFFFF;
    //二维码宽
    private static final int width = 200;
    //二维码高
    private static final int height = 200;
    //二维码生成格式
    private static final String imageType = "jpg";

    public static void main(String[] args) throws Exception {
        String str = "人员编码：1234；人员姓名：李四；";
        zxingCodeCreate(str, "D:/二维码.jpg");
    }

    /**
     * 生成二维码
     * @param text    二维码内容
     * @param outPutPath    二维码生成保存路径
     */
    public static void zxingCodeCreate(String text, String outPutPath){
        Map<EncodeHintType, String> his = new HashMap<EncodeHintType, String>();
        //设置编码字符集
        his.put(EncodeHintType.CHARACTER_SET, "utf-8");
        try {
            //1、生成二维码
            BitMatrix encode = new MultiFormatWriter().encode(text, BarcodeFormat.QR_CODE, width, height, his);

            //2、获取二维码宽高
            int codeWidth = encode.getWidth();
            int codeHeight = encode.getHeight();

            //3、将二维码放入缓冲流
            BufferedImage image = new BufferedImage(codeWidth, codeHeight, BufferedImage.TYPE_INT_RGB);
            for (int i = 0; i < codeWidth; i++) {
                for (int j = 0; j < codeHeight; j++) {
                    //4、循环将二维码内容定入图片
                    image.setRGB(i, j, encode.get(i, j) ? BLACK : WHITE);
                }
            }
            File outPutImage = new File(outPutPath);
            //如果图片不存在创建图片
            if(!outPutImage.exists())
                outPutImage.createNewFile();
            //5、将二维码写入图片
            ImageIO.write(image, imageType, outPutImage);
        } catch (WriterException e) {
            e.printStackTrace();
            logger.info("二维码生成失败");
        } catch (IOException e) {
            e.printStackTrace();
            logger.info("生成二维码图片失败");
        }
    }

    /**
     * 二维码解析
     * @param analyzePath    二维码路径
     * @return
     * @throws IOException
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public static String zxingCodeAnalyze(String analyzePath) throws Exception{
        MultiFormatReader formatReader = new MultiFormatReader();
        String resultStr = null;
        try {
            File file = new File(analyzePath);
            if (!file.exists()) {
                return "二维码不存在";
            }
            BufferedImage image = ImageIO.read(file);
            LuminanceSource source = new BufferedImageLuminanceSource(image);
            Binarizer binarizer = new HybridBinarizer(source);
            BinaryBitmap binaryBitmap = new BinaryBitmap(binarizer);
            Map hints = new HashMap();
            hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
            Result result = formatReader.decode(binaryBitmap, hints);
            resultStr = result.getText();
        } catch (NotFoundException e) {
            e.printStackTrace();
        }
        logger.info(resultStr);
        return resultStr;
    }


}
