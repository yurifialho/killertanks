package com.killertanks.helper.imagens;

import java.awt.Graphics;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.Transparency;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.PixelGrabber;

import javax.swing.ImageIcon;

/**
 * Classe que faz o tratamento das imagens 
 * @author yurifialho
 *
 */
public final class ImagensHelper {
	
	private static ImagensHelper INSTANCE;
	private static ImagensHelper INSTANCEBACKGROUND;
	
	public static int TILES = 0;
	public static int BACKGROUND = 1;
	
	private String file;
	private Image [] images;
	private Image imagem;
	private BufferedImage outImage;
	
	private ImagensHelper(String file){
		this.file = file;
		this.images = new Image[112];
		for(int i = 0; i < images.length; i++){
			images[i] = getTile(i + 1);
		}
		
	}
	
	public static ImagensHelper getInstance(){
		if(INSTANCE == null){
			INSTANCE = new ImagensHelper("/imagens/topview_tiles.png");
		}
		return INSTANCE;
	}
	
	public static ImagensHelper getInstance(int tipo){
		switch (tipo) {
			case 0:{
				if(INSTANCE == null){
					INSTANCE = new ImagensHelper("/imagens/topview_tiles.png");
				}
				return INSTANCE;
			}
			case 1: {
				if(INSTANCEBACKGROUND == null){
					INSTANCEBACKGROUND = new ImagensHelper("/imagens/groundpng1.png");
				}
				return INSTANCEBACKGROUND;
			}
		}
		return null;
	}
	
	public static void main(String[] args) {
		ImagensHelper mg = ImagensHelper.getInstance();
		System.out.println(mg);
	}
	
	public Image getTile(int n) {
        int x, y;
        x = (n - 1) % 16;
        y = (n - 1) / 16;
        return cropImage(x * 16, y * 16, 16, 16);
    }
	
	public Image getImage() {
		try {
			if (imagem == null) {
				imagem = new ImageIcon(getClass().getResource(file)).getImage();
			}
			
			return imagem;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public Image cropImage(int x, int y, int w, int h) {
        try {
            if (imagem == null) {
                imagem = new ImageIcon(getClass().getResource(file)).getImage();
                outImage = makeBufferedImage(imagem);
            }
            BufferedImage img = outImage.getSubimage(x, y, w, h);
            return (Image) img;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
	
	public static BufferedImage makeBufferedImage(Image image) {
        if (image instanceof BufferedImage) {
            return (BufferedImage) image;
        }

        image = new ImageIcon(image).getImage();

        boolean hasAlpha = isAlpha(image);

        BufferedImage bimage = null;
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        try {
            int transparency = Transparency.OPAQUE;
            if (hasAlpha) {
                transparency = Transparency.BITMASK;
            }

            GraphicsDevice gs = ge.getDefaultScreenDevice();
            GraphicsConfiguration gc = gs.getDefaultConfiguration();
            bimage = gc.createCompatibleImage(
                    image.getWidth(null), image.getHeight(null), transparency);
        } catch (HeadlessException e) {
        }

        if (bimage == null) {
            int type = BufferedImage.TYPE_INT_RGB;
            if (hasAlpha) {
                type = BufferedImage.TYPE_INT_ARGB;
            }
            bimage = new BufferedImage(image.getWidth(null), image.getHeight(null), type);
        }

        Graphics g = bimage.createGraphics();

        g.drawImage(image, 0, 0, null);
        g.dispose();

        return bimage;
    }
	
	public static boolean isAlpha(Image image) {
        if (image instanceof BufferedImage) {
            BufferedImage bimage = (BufferedImage) image;
            return bimage.getColorModel().hasAlpha();
        }

        PixelGrabber pg = new PixelGrabber(image, 0, 0, 1, 1, false);
        try {
            pg.grabPixels();
        } catch (InterruptedException e) {
        }

        ColorModel cm = pg.getColorModel();
        return cm.hasAlpha();
    }
}
