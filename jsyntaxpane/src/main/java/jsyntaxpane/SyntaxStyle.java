/*
 * Copyright 2008 Ayman Al-Sairafi ayman.alsairafi@gmail.com
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); 
 * you may not use this file except in compliance with the License. 
 * You may obtain a copy of the License 
 *       at http://www.apache.org/licenses/LICENSE-2.0 
 * Unless required by applicable law or agreed to in writing, software 
 * distributed under the License is distributed on an "AS IS" BASIS, 
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. 
 * See the License for the specific language governing permissions and 
 * limitations under the License.  
 */
package jsyntaxpane;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.GeneralPath;
import javax.swing.text.Segment;
import javax.swing.text.TabExpander;
import javax.swing.text.Utilities;
import javax.swing.text.View;

/**
 * This class represents the Style for a TokenType.  This class is responsible
 * for actually drawing a Token on the View.
 * 
 * @author Ayman Al-Sairafi
 */
public final class SyntaxStyle {

    private Color color;
    private int fontStyle;

    public SyntaxStyle() {
        super();
    }

    public SyntaxStyle(Color color, boolean bold, boolean italic) {
        super();
        this.color = color;
        setBold(bold);
        setItalic(italic);
    }

    public SyntaxStyle(Color color, int fontStyle) {
        super();
        this.color = color;
        this.fontStyle = fontStyle;
    }

    public SyntaxStyle(String str) {
        String[] parts = str.split("\\s*,\\s*");
        if (parts.length != 2) {
            throw new IllegalArgumentException("style not correct format: " + str);
        }
        this.color = new Color(Integer.decode(parts[0]));
        this.fontStyle = Integer.decode(parts[1]);
    }

    public boolean isBold() {
        return (fontStyle & Font.BOLD) != 0;
    }

    public void setBold(Boolean bold) {
        if (bold) {
            fontStyle |= Font.BOLD;
        } else {
            int mask = -1 ^ Font.BOLD;
            fontStyle = (fontStyle & (mask));
        }
    }

    public String getColorString() {
        return String.format("0x%06x", color.getRGB() & 0x00ffffff);
    }

    public void setColorString(String color) {
        this.color = Color.decode(color);
    }

    public Boolean isItalic() {
        return (fontStyle & Font.ITALIC) != 0;
    }

    public void setItalic(Boolean italic) {
        if (italic) {
            fontStyle |= Font.ITALIC;
        } else {
            fontStyle = (fontStyle & (-1 ^ Font.ITALIC));
        }
    }
    
    private boolean drawTabs = true;

    public static final String PROP_DRAWTABS = "drawTabs";

    public boolean isDrawTabs() {
        return drawTabs;
    }

    public void setDrawTabs(boolean drawTabs) {
        this.drawTabs = drawTabs;
    }


    public int getFontStyle() {
        return fontStyle;
    }

    public Color getColor() {
        return color;
    }
    
    
    /**
     * Draw an arrow where tabs are found.
     * 
     * @param g
     * @param s
     * @param metrics
     * @param x  the x of the left side of the segment.
     * @param y  The vertical center of the arrow
     * @param e
     * @param startOffset
     * @return 
     */
    static final int drawTabbedTextWidth( Graphics2D g, Segment s, FontMetrics metrics, int x, int y,
                                        TabExpander e, int startOffset ) {
        int nextX = x;
        char[] txt = s.array;
        int txtOffset = s.offset;
        int n = s.offset + s.count;
        int charCount = 0;
        int spaceAddon = 0;
        int spaceAddonLeftoverEnd = -1;
        int startJustifiableContent = 0;
        int endJustifiableContent = 0;

        int currentX= x;
        for (int i = txtOffset; i < n; i++) {
            if (txt[i] == '\t'
                || ((spaceAddon != 0 || i <= spaceAddonLeftoverEnd)
                    && (txt[i] == ' ')
                    && startJustifiableContent <= i
                    && i <= endJustifiableContent
                    )) {
                nextX += metrics.charsWidth(txt, i-charCount, charCount);
                charCount = 0;
                if (txt[i] == '\t') {
                    if (e != null) {
                        nextX = (int) e.nextTabStop((float) nextX,
                                                    startOffset + i - txtOffset);
                    } else {
                        nextX += metrics.charWidth(' ');
                    }
                } else if (txt[i] == ' ') {
                    nextX += metrics.charWidth(' ') + spaceAddon;
                    if (i <= spaceAddonLeftoverEnd) {
                        nextX++;
                    }
                }
                //g.setColor(Color.LIGHT_GRAY);
                g.setColor(new Color(220, 220, 220));
                int dx= 3;
                int hy= y;
                //int hx= nextX-2;
                int hx= nextX-4;
                GeneralPath p= new GeneralPath();
                p.moveTo( hx, hy );        
                //p.lineTo( (hx-2*dx), (hy-dx-1) ); // triangle
                //p.lineTo( (hx-2*dx), (hy+dx+1) ); // triangle
                p.lineTo( hx, hy );            
                g.fill( p );
                g.drawLine( currentX+2, y, (int)hx-2, y );
                currentX= nextX;
                
            } else if(txt[i] == '\n') {
            // Ignore newlines, they take up space and we shouldn't be
            // counting them.
                nextX += metrics.charsWidth(txt, i - charCount, charCount);
                charCount = 0;
                currentX= 0;
            } else {
                currentX+= metrics.charsWidth(txt, i, 1);
                charCount++;
            }
        }
        nextX += metrics.charsWidth(txt, n - charCount, charCount);
        return nextX - x;
    }
    
    /**
     * Draw text.  This can directly call the Utilities.drawTabbedText.
     * Sub-classes can override this method to provide any other decorations.
     * @param  segment - the source of the text
     * @param  x - the X origin >= 0
     * @param  y - the Y origin >= 0
     * @param  graphics - the graphics context
     * @param e - how to expand the tabs. If this value is null, tabs will be 
     * expanded as a space character.
     * @param startOffset - starting offset of the text in the document >= 0 
     * @return
     */
    public int drawText(Segment segment, int x, int y,
            Graphics graphics, TabExpander e, int startOffset) {
        graphics.setFont(graphics.getFont().deriveFont(getFontStyle()));
        FontMetrics fontMetrics = graphics.getFontMetrics();
        int a = fontMetrics.getAscent();
        int h = a + fontMetrics.getDescent();
        int w = Utilities.getTabbedTextWidth(segment, fontMetrics, 0, e, startOffset);
        int rX = x - 1;
        int rY = y - a;
        int rW = w + 2;
        int rH = h;
        if ((getFontStyle() & 0x10) != 0) {
            graphics.setColor(Color.decode("#EEEEEE"));
            graphics.fillRect(rX, rY, rW, rH);
        }
        
        if ( drawTabs ) {
            drawTabbedTextWidth( (Graphics2D)(graphics.create()), segment, fontMetrics, x, y-fontMetrics.getAscent()/2, e, startOffset );
        }
                
        graphics.setColor(getColor());
        x = Utilities.drawTabbedText(segment, x, y, graphics, e, startOffset);
        if ((getFontStyle() & 0x8) != 0) {
            graphics.setColor(Color.RED);
            graphics.drawRect(rX, rY, rW, rH);
        }
        return x;
    }
}
