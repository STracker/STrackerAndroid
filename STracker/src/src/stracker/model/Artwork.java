package src.stracker.model;

public class Artwork {
	private byte[] Image;
    private String ImageMimeType;
	
    public Artwork(byte[] image, String imageMimeType) {
		Image = image;
		ImageMimeType = imageMimeType;
	}

	public byte[] getImage() {
		return Image;
	}

	public void setImage(byte[] image) {
		Image = image;
	}

	public String getImageMimeType() {
		return ImageMimeType;
	}

	public void setImageMimeType(String imageMimeType) {
		ImageMimeType = imageMimeType;
	}
}
