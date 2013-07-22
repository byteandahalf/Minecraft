package net.minecraft.src;


public class GuiTextField extends Gui
{
	private final FontRenderer fontRenderer;
	private final int xPos;
	private final int yPos;
	private final int width;
	private final int height;
	private String text = "";
	private int maxStringLength = 32;
	private int cursorCounter;
	private boolean enableBackgroundDrawing = true;
	private boolean canLoseFocus = true;
	private boolean isFocused = false;
	private boolean isEnabled = true;
	private int lineScrollOffset = 0;
	private int cursorPosition = 0;
	private int selectionEnd = 0;
	private int enabledColor = 14737632;
	private int disabledColor = 7368816;
	private boolean visible = true;
	
	public GuiTextField(FontRenderer p_i3050_1_, int p_i3050_2_, int p_i3050_3_, int p_i3050_4_, int p_i3050_5_)
	{
		fontRenderer = p_i3050_1_;
		xPos = p_i3050_2_;
		yPos = p_i3050_3_;
		width = p_i3050_4_;
		height = p_i3050_5_;
	}
	
	public void deleteFromCursor(int par1)
	{
		if(text.length() != 0)
		{
			if(selectionEnd != cursorPosition)
			{
				writeText("");
			} else
			{
				boolean var2 = par1 < 0;
				int var3 = var2 ? cursorPosition + par1 : cursorPosition;
				int var4 = var2 ? cursorPosition : cursorPosition + par1;
				String var5 = "";
				if(var3 >= 0)
				{
					var5 = text.substring(0, var3);
				}
				if(var4 < text.length())
				{
					var5 = var5 + text.substring(var4);
				}
				text = var5;
				if(var2)
				{
					moveCursorBy(par1);
				}
			}
		}
	}
	
	public void deleteWords(int par1)
	{
		if(text.length() != 0)
		{
			if(selectionEnd != cursorPosition)
			{
				writeText("");
			} else
			{
				deleteFromCursor(getNthWordFromCursor(par1) - cursorPosition);
			}
		}
	}
	
	private void drawCursorVertical(int par1, int par2, int par3, int par4)
	{
		int var5;
		if(par1 < par3)
		{
			var5 = par1;
			par1 = par3;
			par3 = var5;
		}
		if(par2 < par4)
		{
			var5 = par2;
			par2 = par4;
			par4 = var5;
		}
		Tessellator var6 = Tessellator.instance;
		GL11.glColor4f(0.0F, 0.0F, 255.0F, 255.0F);
		GL11.glDisable(GL11.GL_TEXTURE_2D);
		GL11.glEnable(GL11.GL_COLOR_LOGIC_OP);
		GL11.glLogicOp(GL11.GL_OR_REVERSE);
		var6.startDrawingQuads();
		var6.addVertex(par1, par4, 0.0D);
		var6.addVertex(par3, par4, 0.0D);
		var6.addVertex(par3, par2, 0.0D);
		var6.addVertex(par1, par2, 0.0D);
		var6.draw();
		GL11.glDisable(GL11.GL_COLOR_LOGIC_OP);
		GL11.glEnable(GL11.GL_TEXTURE_2D);
	}
	
	public void drawTextBox()
	{
		if(getVisible())
		{
			if(getEnableBackgroundDrawing())
			{
				drawRect(xPos - 1, yPos - 1, xPos + width + 1, yPos + height + 1, -6250336);
				drawRect(xPos, yPos, xPos + width, yPos + height, -16777216);
			}
			int var1 = isEnabled ? enabledColor : disabledColor;
			int var2 = cursorPosition - lineScrollOffset;
			int var3 = selectionEnd - lineScrollOffset;
			String var4 = fontRenderer.trimStringToWidth(text.substring(lineScrollOffset), getWidth());
			boolean var5 = var2 >= 0 && var2 <= var4.length();
			boolean var6 = isFocused && cursorCounter / 6 % 2 == 0 && var5;
			int var7 = enableBackgroundDrawing ? xPos + 4 : xPos;
			int var8 = enableBackgroundDrawing ? yPos + (height - 8) / 2 : yPos;
			int var9 = var7;
			if(var3 > var4.length())
			{
				var3 = var4.length();
			}
			if(var4.length() > 0)
			{
				String var10 = var5 ? var4.substring(0, var2) : var4;
				var9 = fontRenderer.drawStringWithShadow(var10, var7, var8, var1);
			}
			boolean var13 = cursorPosition < text.length() || text.length() >= getMaxStringLength();
			int var11 = var9;
			if(!var5)
			{
				var11 = var2 > 0 ? var7 + width : var7;
			} else if(var13)
			{
				var11 = var9 - 1;
				--var9;
			}
			if(var4.length() > 0 && var5 && var2 < var4.length())
			{
				fontRenderer.drawStringWithShadow(var4.substring(var2), var9, var8, var1);
			}
			if(var6)
			{
				if(var13)
				{
					Gui.drawRect(var11, var8 - 1, var11 + 1, var8 + 1 + fontRenderer.FONT_HEIGHT, -3092272);
				} else
				{
					fontRenderer.drawStringWithShadow("_", var11, var8, var1);
				}
			}
			if(var3 != var2)
			{
				int var12 = var7 + fontRenderer.getStringWidth(var4.substring(0, var3));
				drawCursorVertical(var11, var8 - 1, var12 - 1, var8 + 1 + fontRenderer.FONT_HEIGHT);
			}
		}
	}
	
	public int func_73798_a(int par1, int par2, boolean par3)
	{
		int var4 = par2;
		boolean var5 = par1 < 0;
		int var6 = Math.abs(par1);
		for(int var7 = 0; var7 < var6; ++var7)
		{
			if(var5)
			{
				while(par3 && var4 > 0 && text.charAt(var4 - 1) == 32)
				{
					--var4;
				}
				while(var4 > 0 && text.charAt(var4 - 1) != 32)
				{
					--var4;
				}
			} else
			{
				int var8 = text.length();
				var4 = text.indexOf(32, var4);
				if(var4 == -1)
				{
					var4 = var8;
				} else
				{
					while(par3 && var4 < var8 && text.charAt(var4) == 32)
					{
						++var4;
					}
				}
			}
		}
		return var4;
	}
	
	public int getCursorPosition()
	{
		return cursorPosition;
	}
	
	public boolean getEnableBackgroundDrawing()
	{
		return enableBackgroundDrawing;
	}
	
	public int getMaxStringLength()
	{
		return maxStringLength;
	}
	
	public int getNthWordFromCursor(int par1)
	{
		return getNthWordFromPos(par1, getCursorPosition());
	}
	
	public int getNthWordFromPos(int par1, int par2)
	{
		return func_73798_a(par1, getCursorPosition(), true);
	}
	
	public String getSelectedtext()
	{
		int var1 = cursorPosition < selectionEnd ? cursorPosition : selectionEnd;
		int var2 = cursorPosition < selectionEnd ? selectionEnd : cursorPosition;
		return text.substring(var1, var2);
	}
	
	public int getSelectionEnd()
	{
		return selectionEnd;
	}
	
	public String getText()
	{
		return text;
	}
	
	public boolean getVisible()
	{
		return visible;
	}
	
	public int getWidth()
	{
		return getEnableBackgroundDrawing() ? width - 8 : width;
	}
	
	public boolean isFocused()
	{
		return isFocused;
	}
	
	public void mouseClicked(int par1, int par2, int par3)
	{
		boolean var4 = par1 >= xPos && par1 < xPos + width && par2 >= yPos && par2 < yPos + height;
		if(canLoseFocus)
		{
			setFocused(isEnabled && var4);
		}
		if(isFocused && par3 == 0)
		{
			int var5 = par1 - xPos;
			if(enableBackgroundDrawing)
			{
				var5 -= 4;
			}
			String var6 = fontRenderer.trimStringToWidth(text.substring(lineScrollOffset), getWidth());
			setCursorPosition(fontRenderer.trimStringToWidth(var6, var5).length() + lineScrollOffset);
		}
	}
	
	public void moveCursorBy(int par1)
	{
		setCursorPosition(selectionEnd + par1);
	}
	
	public void setCanLoseFocus(boolean par1)
	{
		canLoseFocus = par1;
	}
	
	public void setCursorPosition(int par1)
	{
		cursorPosition = par1;
		int var2 = text.length();
		if(cursorPosition < 0)
		{
			cursorPosition = 0;
		}
		if(cursorPosition > var2)
		{
			cursorPosition = var2;
		}
		setSelectionPos(cursorPosition);
	}
	
	public void setCursorPositionEnd()
	{
		setCursorPosition(text.length());
	}
	
	public void setCursorPositionZero()
	{
		setCursorPosition(0);
	}
	
	public void setDisabledTextColour(int par1)
	{
		disabledColor = par1;
	}
	
	public void setEnableBackgroundDrawing(boolean par1)
	{
		enableBackgroundDrawing = par1;
	}
	
	public void setEnabled(boolean par1)
	{
		isEnabled = par1;
	}
	
	public void setFocused(boolean par1)
	{
		if(par1 && !isFocused)
		{
			cursorCounter = 0;
		}
		isFocused = par1;
	}
	
	public void setMaxStringLength(int par1)
	{
		maxStringLength = par1;
		if(text.length() > par1)
		{
			text = text.substring(0, par1);
		}
	}
	
	public void setSelectionPos(int par1)
	{
		int var2 = text.length();
		if(par1 > var2)
		{
			par1 = var2;
		}
		if(par1 < 0)
		{
			par1 = 0;
		}
		selectionEnd = par1;
		if(fontRenderer != null)
		{
			if(lineScrollOffset > var2)
			{
				lineScrollOffset = var2;
			}
			int var3 = getWidth();
			String var4 = fontRenderer.trimStringToWidth(text.substring(lineScrollOffset), var3);
			int var5 = var4.length() + lineScrollOffset;
			if(par1 == lineScrollOffset)
			{
				lineScrollOffset -= fontRenderer.trimStringToWidth(text, var3, true).length();
			}
			if(par1 > var5)
			{
				lineScrollOffset += par1 - var5;
			} else if(par1 <= lineScrollOffset)
			{
				lineScrollOffset -= lineScrollOffset - par1;
			}
			if(lineScrollOffset < 0)
			{
				lineScrollOffset = 0;
			}
			if(lineScrollOffset > var2)
			{
				lineScrollOffset = var2;
			}
		}
	}
	
	public void setText(String par1Str)
	{
		if(par1Str.length() > maxStringLength)
		{
			text = par1Str.substring(0, maxStringLength);
		} else
		{
			text = par1Str;
		}
		setCursorPositionEnd();
	}
	
	public void setTextColor(int par1)
	{
		enabledColor = par1;
	}
	
	public void setVisible(boolean par1)
	{
		visible = par1;
	}
	
	public boolean textboxKeyTyped(char par1, int par2)
	{
		if(isEnabled && isFocused)
		{
			switch(par1)
			{
				case 1:
					setCursorPositionEnd();
					setSelectionPos(0);
					return true;
				case 3:
					GuiScreen.setClipboardString(getSelectedtext());
					return true;
				case 22:
					writeText(GuiScreen.getClipboardString());
					return true;
				case 24:
					GuiScreen.setClipboardString(getSelectedtext());
					writeText("");
					return true;
				default:
					switch(par2)
					{
						case 14:
							if(GuiScreen.isCtrlKeyDown())
							{
								deleteWords(-1);
							} else
							{
								deleteFromCursor(-1);
							}
							return true;
						case 199:
							if(GuiScreen.isShiftKeyDown())
							{
								setSelectionPos(0);
							} else
							{
								setCursorPositionZero();
							}
							return true;
						case 203:
							if(GuiScreen.isShiftKeyDown())
							{
								if(GuiScreen.isCtrlKeyDown())
								{
									setSelectionPos(getNthWordFromPos(-1, getSelectionEnd()));
								} else
								{
									setSelectionPos(getSelectionEnd() - 1);
								}
							} else if(GuiScreen.isCtrlKeyDown())
							{
								setCursorPosition(getNthWordFromCursor(-1));
							} else
							{
								moveCursorBy(-1);
							}
							return true;
						case 205:
							if(GuiScreen.isShiftKeyDown())
							{
								if(GuiScreen.isCtrlKeyDown())
								{
									setSelectionPos(getNthWordFromPos(1, getSelectionEnd()));
								} else
								{
									setSelectionPos(getSelectionEnd() + 1);
								}
							} else if(GuiScreen.isCtrlKeyDown())
							{
								setCursorPosition(getNthWordFromCursor(1));
							} else
							{
								moveCursorBy(1);
							}
							return true;
						case 207:
							if(GuiScreen.isShiftKeyDown())
							{
								setSelectionPos(text.length());
							} else
							{
								setCursorPositionEnd();
							}
							return true;
						case 211:
							if(GuiScreen.isCtrlKeyDown())
							{
								deleteWords(1);
							} else
							{
								deleteFromCursor(1);
							}
							return true;
						default:
							if(ChatAllowedCharacters.isAllowedCharacter(par1))
							{
								writeText(Character.toString(par1));
								return true;
							} else return false;
					}
			}
		} else return false;
	}
	
	public void updateCursorCounter()
	{
		++cursorCounter;
	}
	
	public void writeText(String par1Str)
	{
		String var2 = "";
		String var3 = ChatAllowedCharacters.filerAllowedCharacters(par1Str);
		int var4 = cursorPosition < selectionEnd ? cursorPosition : selectionEnd;
		int var5 = cursorPosition < selectionEnd ? selectionEnd : cursorPosition;
		int var6 = maxStringLength - text.length() - (var4 - selectionEnd);
		boolean var7 = false;
		if(text.length() > 0)
		{
			var2 = var2 + text.substring(0, var4);
		}
		int var8;
		if(var6 < var3.length())
		{
			var2 = var2 + var3.substring(0, var6);
			var8 = var6;
		} else
		{
			var2 = var2 + var3;
			var8 = var3.length();
		}
		if(text.length() > 0 && var5 < text.length())
		{
			var2 = var2 + text.substring(var5);
		}
		text = var2;
		moveCursorBy(var4 - selectionEnd + var8);
	}
}
