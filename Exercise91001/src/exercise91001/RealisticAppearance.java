package exercise91001;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.media.j3d.Appearance;
import javax.media.j3d.ImageComponent2D;
import javax.media.j3d.Material;
import javax.media.j3d.TexCoordGeneration;
import javax.media.j3d.Texture;
import javax.media.j3d.Texture2D;
import javax.media.j3d.TextureAttributes;

import com.sun.j3d.utils.image.TextureLoader;

public class RealisticAppearance extends Appearance {
	
	public RealisticAppearance(String path) {
		
		BufferedImage image = null;
		URL url = getClass().getClassLoader().getResource(path);
		try {
			image = ImageIO.read(url);
		} catch (IOException ex) {
			ex.printStackTrace();
		}		
		
		TextureLoader tl = new TextureLoader(image, "RGB");
		ImageComponent2D RGBimage = tl.getImage();
		
		Texture2D texture = new Texture2D(Texture.BASE_LEVEL, Texture.RGBA, RGBimage.getWidth(), RGBimage.getHeight());
		texture.setImage(0, RGBimage);
		texture.setEnable(true);
		texture.setMagFilter(Texture.BASE_LEVEL_LINEAR);
		texture.setMinFilter(Texture.BASE_LEVEL_LINEAR);		
		setTexture(texture);
				
		TextureAttributes textAtt = new TextureAttributes();
		textAtt.setTextureMode(TextureAttributes.COMBINE);
		setTextureAttributes(textAtt);
		
		setMaterial(new Material());
		
	}

	// carrega a imagem no URL e devolve aparencia com textura como reflexo
	private Appearance createReflexAppearance(String url) {
		// caminho para o ficheiro
		// com this
		// URL file = getClass().getClassLoader().getResource(url);
		// TextureLoader loader = new TextureLoader(file, this);

		// com bufferedimage
		BufferedImage buffImage = null;
		java.net.URL pathFile = getClass().getClassLoader().getResource(url);
		try {
			buffImage = ImageIO.read(pathFile);
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		// jpg -> formato RGB
		TextureLoader loader = new TextureLoader(buffImage, "RGB");
		ImageComponent2D image = loader.getImage();

		// cria aparencia com textura
		Texture2D texture = new Texture2D(Texture.BASE_LEVEL, Texture.RGBA, image.getWidth(), image.getHeight());
		texture.setImage(0, image);
		texture.setEnable(true);
		texture.setMagFilter(Texture.BASE_LEVEL_LINEAR);
		texture.setMinFilter(Texture.BASE_LEVEL_LINEAR);
		Appearance ap = new Appearance();
		ap.setTexture(texture);
		ap.setMaterial(new Material());

		// textura + iluminacao
		TextureAttributes textAtt = new TextureAttributes();
		textAtt.setTextureMode(TextureAttributes.COMBINE); // COMBINE
		ap.setTextureAttributes(textAtt);
		ap.setMaterial(new Material());

		// gera coordenadas de textura comn reflexao
		TexCoordGeneration tcg = new TexCoordGeneration();
		tcg.setGenMode(TexCoordGeneration.REFLECTION_MAP);
		ap.setTexCoordGeneration(tcg);

		// devolve aparencia com textura
		return ap;
	}
}
