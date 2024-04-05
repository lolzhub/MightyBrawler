package ui;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import utilz.LoadSave;
import static utilz.Constants.UI.VolumeButtons.*;

public class VolumeButton extends PauseButton {
	// Array to hold images for the button
	private BufferedImage[] imgs;
	// Image for the slider
	private BufferedImage slider;
	// Index of the current image to be displayed
	private int index = 0;
	// Flags to track mouse interaction states
	private boolean mouseOver, mousePressed;
	// X-coordinate of the button's center
	private int buttonX;
	// Minimum and maximum X-coordinates for the button's movement range
	private int minX, maxX;
	// Current floating-point value representing the position of the slider within the button's range
	private float floatValue = 0f;

	// Constructor
	public VolumeButton(int x, int y, int width, int height) {
		// Call superclass constructor
		super(x + width / 2, y, VOLUME_WIDTH, height);
		// Adjust the button's bounds to center it
		bounds.x -= VOLUME_WIDTH / 2;
		// Calculate the X-coordinate of the button's center
		buttonX = x + width / 2;
		// Initialize button's X-coordinate and width
		this.x = x;
		this.width = width;
		// Calculate minimum and maximum X-coordinates for the button's movement range
		minX = x + VOLUME_WIDTH / 2;
		maxX = x + width - VOLUME_WIDTH / 2;
		// Load button images
		loadImgs();
	}

	// Method to load button images from sprite atlas
	private void loadImgs() {
		// Load sprite atlas containing button images
		BufferedImage temp = LoadSave.GetSpriteAtlas(LoadSave.VOLUME_BUTTONS);
		// Initialize array to hold button images
		imgs = new BufferedImage[3];
		// Extract individual button images from sprite atlas
		for (int i = 0; i < imgs.length; i++)
			imgs[i] = temp.getSubimage(i * VOLUME_DEFAULT_WIDTH, 0, VOLUME_DEFAULT_WIDTH, VOLUME_DEFAULT_HEIGHT);

		// Extract the slider image from the sprite atlas
		slider = temp.getSubimage(3 * VOLUME_DEFAULT_WIDTH, 0, SLIDER_DEFAULT_WIDTH, VOLUME_DEFAULT_HEIGHT);
	}

	// Method to update the state of the button
	public void update() {
		// Default to first image index
		index = 0;
		// Update image index based on mouse interaction states
		if (mouseOver)
			index = 1; // Mouse over image index
		if (mousePressed)
			index = 2; // Mouse pressed image index
	}

	// Method to draw the button and slider
	public void draw(Graphics g) {
		// Draw the slider image
		g.drawImage(slider, x, y, width, height, null);
		// Draw the button image
		g.drawImage(imgs[index], buttonX - VOLUME_WIDTH / 2, y, VOLUME_WIDTH, height, null);
	}

	// Method to change the X-coordinate of the button
	public void changeX(int x) {
		// Ensure that the button stays within its movement range
		if (x < minX)
			buttonX = minX;
		else if (x > maxX)
			buttonX = maxX;
		else
			buttonX = x;
		// Update the floating-point value representing the position of the slider
		updateFloatValue();
		// Update the button's bounds
		bounds.x = buttonX - VOLUME_WIDTH / 2;
	}

	// Method to update the floating-point value representing the position of the slider
	private void updateFloatValue() {
		// Calculate the range of the slider's movement
		float range = maxX - minX;
		// Calculate the value of the slider's position within the range
		float value = buttonX - minX;
		// Calculate the floating-point value representing the position of the slider
		floatValue = value / range;
	}

	// Method to reset mouse interaction flags
	public void resetBools() {
		mouseOver = false;
		mousePressed = false;
	}

	// Getter for mouseOver flag
	public boolean isMouseOver() {
		return mouseOver;
	}

	// Setter for mouseOver flag
	public void setMouseOver(boolean mouseOver) {
		this.mouseOver = mouseOver;
	}

	// Getter for mousePressed flag
	public boolean isMousePressed() {
		return mousePressed;
	}

	// Setter for mousePressed flag
	public void setMousePressed(boolean mousePressed) {
		this.mousePressed = mousePressed;
	}

	// Getter for the floating-point value representing the position of the slider
	public float getFloatValue() {
		return floatValue;
	}
}
