package com.example.backend.utils;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;


import java.awt.image.BufferedImage;

public class QRGenerator {
  static int width = 300;
  static int height = 300;
  private static final int BLACK = 0xFF000000;
  private static final int WHITE = 0xFFFFFFFF;


  static QRCodeWriter qrCodeWriter = new QRCodeWriter();
  public static BufferedImage generateQRCode(String text) {
        try {
          BitMatrix bitMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, width, height);
          BufferedImage qrCodeImage = toBufferedImage(bitMatrix);
          return qrCodeImage;
        } catch (Exception e) {
            e.printStackTrace();
        }
    return null;
  }

  private static BufferedImage toBufferedImage(BitMatrix matrix) {
    int width = matrix.getWidth();
    int height = matrix.getHeight();
    BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
    for (int x = 0; x < width; x++) {
      for (int y = 0; y < height; y++) {
        image.setRGB(x, y, matrix.get(x, y) ? BLACK : WHITE);
      }
    }
    return image;
  }
}

