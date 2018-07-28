
package com.ait.lienzo.charts.shared.core.types.palettes;

import com.ait.lienzo.charts.client.core.ColorPalette;
import com.ait.lienzo.shared.core.types.Color;
import com.ait.lienzo.shared.core.types.ColorName;
import com.ait.lienzo.shared.core.types.IColor;
import com.ait.tooling.nativetools.client.collection.NFastArrayList;

public class PatternFlyPalette extends ColorPalette
{
    private NFastArrayList<Color[]> m_colors;
    
    public PatternFlyPalette()
    {
        m_colors = new NFastArrayList<>();
        //m_colors.add(BLACKS);
        //m_colors.add(CYANS);
        m_colors.add(REDS);
        m_colors.add(BLUES);
        m_colors.add(GREENS);
        m_colors.add(ORANGES);
        m_colors.add(LIGHTGREENS);
        m_colors.add(GOLDS);
        m_colors.add(LIGHTBLUES);
        m_colors.add(PURPLE);
        m_colors.add(CYANS);
    }

    @Override
    public IColor getTooltipBackgroundColor()
    {
        return TooltipBackground;
    }

    @Override
    public IColor getTooltipTextColor()
    {
        return ColorName.WHITE;
    }
    
    @Override
    public IColor getColor(int colorIndex)
    {
        int[] currentIndexes = new int[m_colors.size()];
        IColor color = null;
        int colorGlobalIndex = 0;
        for (int i = 0; i < m_colors.size(); i++)
        {
            int index = currentIndexes[i];
            if (index < m_colors.get(i).length)
            {
                color = m_colors.get(i)[index];
                currentIndexes[i]++;
            }

            if (colorGlobalIndex == colorIndex)
            {
                break;
            }

            colorGlobalIndex++;
        }

        return color;
    }

    //    @Override
    //    public IColor getNextColor()
    //    {
    //        if (m_currentColorsGroup > m_colors.size()) 
    //        {
    //            m_currentColorsGroup = 0;
    //        }
    //        
    //        IColor color = m_colors.get(m_currentColorsGroup).getNext();
    //        
    //        m_currentColorsGroup++;
    //        
    //        return color;
    //    }

    @Override
    public IColor getBordersColor()
    {
        return ColorName.WHITE;
    }

    private static final Color TooltipBackground = new Color(77, 82, 88, 0.9);
    
    private static final Color   Black100      = new Color(250, 250, 250);                                                                                                                                                                                                                                                               // pf-black-100 #fafafa

    private static final Color   Black150      = new Color(245, 245, 245);                                                                                                                                                                                                                                                               // pf-black-150 #f5f5f5

    private static final Color   Black200      = new Color(237, 237, 237);                                                                                                                                                                                                                                                               // pf-black-200 #ededed

    private static final Color   Black300      = new Color(209, 209, 209);                                                                                                                                                                                                                                                               // pf-black-300 #d1d1d1

    private static final Color   Black400      = new Color(187, 187, 187);                                                                                                                                                                                                                                                               // pf-black-400 #bbbbbb

    private static final Color   Black500      = new Color(139, 141, 143);                                                                                                                                                                                                                                                               // pf-black-500 #8b8d8f

    private static final Color   Black600      = new Color(114, 118, 123);                                                                                                                                                                                                                                                               // pf-black-600 #72767b

    private static final Color   Black700      = new Color(77, 82, 88);                                                                                                                                                                                                                                                                           // pf-black-700 #4d5258

    private static final Color   Black800      = new Color(57, 63, 68);                                                                                                                                                                                                                                                                           // pf-black-800 #393f44

    private static final Color   Black900      = new Color(41, 46, 52);                                                                                                                                                                                                                                                                           // pf-black-900 #292e34

    private static final Color   Black         = new Color(3, 3, 3);                                                                                                                                                                                                                                                                                       // pf-black #030303

    private static final Color   Red100        = new Color(204, 0, 0);                                                                                                                                                                                                                                                                               // pf-red-100 #cc0000

    private static final Color   Red200        = new Color(163, 0, 0);                                                                                                                                                                                                                                                                               // pf-red-200 #a30000

    private static final Color   Red300        = new Color(139, 0, 0);                                                                                                                                                                                                                                                                               // pf-red-300 #8b0000

    private static final Color   Red400        = new Color(71, 0, 0);                                                                                                                                                                                                                                                                                   // pf-red-400 #470000

    private static final Color   Red500        = new Color(44, 0, 0);                                                                                                                                                                                                                                                                                   // pf-red-500 #2c0000

    private static final Color   Orange100     = new Color(251, 222, 191);                                                                                                                                                                                                                                                               // pf-orange-100 #fbdebf

    private static final Color   Orange200     = new Color(247, 189, 127);                                                                                                                                                                                                                                                               // pf-orange-200 #f7bd7f

    private static final Color   Orange300     = new Color(243, 157, 60);                                                                                                                                                                                                                                                                   // pf-orange-300 #f39d3c

    private static final Color   Orange400     = new Color(236, 122, 8);                                                                                                                                                                                                                                                                       // pf-orange-400 #ec7a08

    private static final Color   Orange500     = new Color(179, 92, 0);                                                                                                                                                                                                                                                                           // pf-orange-500 #b35c00

    private static final Color   Orange600     = new Color(119, 61, 0);                                                                                                                                                                                                                                                                           // pf-orange-600 #773d00

    private static final Color   Orange700     = new Color(59, 31, 0);                                                                                                                                                                                                                                                                               // pf-orange-700 #3b1f00

    private static final Color   Gold100       = new Color(251, 234, 188);                                                                                                                                                                                                                                                               // pf-gold-100 #fbeabc

    private static final Color   Gold200       = new Color(249, 214, 122);                                                                                                                                                                                                                                                               // pf-gold-200 #f9d67a

    private static final Color   Gold300       = new Color(245, 193, 46);                                                                                                                                                                                                                                                                   // pf-gold-300 #f5c12e

    private static final Color   Gold400       = new Color(240, 171, 0);                                                                                                                                                                                                                                                                       //pf-gold-400 #f0ab00

    private static final Color   Gold500       = new Color(181, 129, 0);                                                                                                                                                                                                                                                                       // pf-gold-500 #b58100

    private static final Color   Gold600       = new Color(121, 86, 0);                                                                                                                                                                                                                                                                           // pf-gold-600 #795600

    private static final Color   Gold700       = new Color(61, 44, 0);                                                                                                                                                                                                                                                                               // pf-gold-700 #3d2c00

    private static final Color   LightGreen100 = new Color(228, 245, 188);                                                                                                                                                                                                                                                               // pf-light-green-100 #e4f5bc

    private static final Color   LightGreen200 = new Color(200, 235, 121);                                                                                                                                                                                                                                                               // pf-light-green-200 #c8eb79

    private static final Color   LightGreen300 = new Color(172, 225, 46);                                                                                                                                                                                                                                                                   //pf-light-green-300 #ace12e

    private static final Color   LightGreen400 = new Color(146, 212, 0);                                                                                                                                                                                                                                                                       //pf-light-green-400 #92d400

    private static final Color   LightGreen500 = new Color(108, 161, 0);                                                                                                                                                                                                                                                                       //pf-light-green-500 #6ca100

    private static final Color   LightGreen600 = new Color(72, 107, 0);                                                                                                                                                                                                                                                                           //pf-light-green-600 #486b00

    private static final Color   LightGreen700 = new Color(37, 54, 0);                                                                                                                                                                                                                                                                               //pf-light-green-700 #253600

    private static final Color   Green100      = new Color(207, 231, 205);                                                                                                                                                                                                                                                               //pf-green-100 #cfe7cd

    private static final Color   Green200      = new Color(158, 207, 153);                                                                                                                                                                                                                                                               // pf-green-200 #9ecf99

    private static final Color   Green300      = new Color(110, 182, 100);                                                                                                                                                                                                                                                               // pf-green-300 #6ec664

    private static final Color   Green400      = new Color(63, 156, 53);                                                                                                                                                                                                                                                                       // pf-green-400 #3f9c35

    private static final Color   Green500      = new Color(45, 118, 35);                                                                                                                                                                                                                                                                       // pf-green-500 #2d7623

    private static final Color   Green600      = new Color(30, 79, 24);                                                                                                                                                                                                                                                                           // pf-green-600 #1e4f18

    private static final Color   Green700      = new Color(15, 40, 13);                                                                                                                                                                                                                                                                           //pf-green-700 #0f280d

    private static final Color   Cyan100       = new Color(190, 222, 225);                                                                                                                                                                                                                                                               // pf-cyan-100 #bedee1

    private static final Color   Cyan200       = new Color(125, 189, 195);                                                                                                                                                                                                                                                               //pf-cyan-200 #7dbdc3

    private static final Color   Cyan300       = new Color(58, 156, 166);                                                                                                                                                                                                                                                                   // pf-cyan-300 #3a9ca6

    private static final Color   Cyan400       = new Color(0, 122, 135);                                                                                                                                                                                                                                                                       //pf-cyan-400 #007a87

    private static final Color   Cyan500       = new Color(0, 92, 102);                                                                                                                                                                                                                                                                           //pf-cyan-500 #005c66

    private static final Color   Cyan600       = new Color(0, 61, 68);                                                                                                                                                                                                                                                                               // pf-cyan-600 #003d44

    private static final Color   Cyan700       = new Color(0, 31, 34);                                                                                                                                                                                                                                                                               //pf-cyan-700 #001f22

    private static final Color   LightBlue100  = new Color(190, 237, 249);                                                                                                                                                                                                                                                               // pf-light-blue-100 #beedf9

    private static final Color   LightBlue200  = new Color(124, 219, 243);                                                                                                                                                                                                                                                               // pf-light-blue-200 #7cdbf3

    private static final Color   LightBlue300  = new Color(53, 202, 237);                                                                                                                                                                                                                                                                   // pf-light-blue-300 #35caed

    private static final Color   LightBlue400  = new Color(0, 185, 228);                                                                                                                                                                                                                                                                       //pf-light-blue-400 #00b9e4

    private static final Color   LightBlue500  = new Color(0, 139, 173);                                                                                                                                                                                                                                                                       // pf-light-blue-500 #008bad

    private static final Color   LightBlue600  = new Color(0, 92, 115);                                                                                                                                                                                                                                                                           // pf-light-blue-600 #005c73

    private static final Color   LightBlue700  = new Color(0, 45, 57);                                                                                                                                                                                                                                                                               //pf-light-blue-700 #002d39

    private static final Color   Blue50        = new Color(222, 243, 255);                                                                                                                                                                                                                                                               //pf-blue-50 #def3ff

    private static final Color   Blue100       = new Color(190, 225, 244);                                                                                                                                                                                                                                                               //pf-blue-100 #bee1f4

    private static final Color   Blue200       = new Color(125, 195, 232);                                                                                                                                                                                                                                                               //pf-blue-200 #7dc3e8

    private static final Color   Blue300       = new Color(57, 165, 220);                                                                                                                                                                                                                                                                   // pf-blue-300 #39a5dc

    private static final Color   Blue400       = new Color(0, 136, 206);                                                                                                                                                                                                                                                                       // pf-blue-400 #0088ce

    private static final Color   Blue500       = new Color(0, 101, 156);                                                                                                                                                                                                                                                                       //pf-blue-500 #00659c

    private static final Color   Blue600       = new Color(0, 67, 104);                                                                                                                                                                                                                                                                           // pf-blue-600 #004368

    private static final Color   Blue700       = new Color(0, 34, 53);                                                                                                                                                                                                                                                                               //pf-blue-700 #002235

    private static final Color   Purple100     = new Color(199, 191, 255);                                                                                                                                                                                                                                                               //pf-purple-100 #c7bfff

    private static final Color   Purple200     = new Color(161, 143, 255);                                                                                                                                                                                                                                                               // pf-purple-200 #a18fff

    private static final Color   Purple300     = new Color(132, 97, 247);                                                                                                                                                                                                                                                                   // pf-purple-300 #8461f7

    private static final Color   Purple400     = new Color(112, 63, 236);                                                                                                                                                                                                                                                                   // pf-purple-400 #703fec

    private static final Color   Purple500     = new Color(88, 47, 192);                                                                                                                                                                                                                                                                       //pf-purple-500 #582fc0

    private static final Color   Purple600     = new Color(64, 25, 154);                                                                                                                                                                                                                                                                       // pf-purple-600 #40199a

    private static final Color   Purple700     = new Color(31, 0, 102);                                                                                                                                                                                                                                                                           // pf-purple-700 #1f0066

    private static final Color[] ORANGES       = { Orange400, Orange300, Orange100, Orange200, Orange500, Orange600, Orange700 };

    private static final Color[] GOLDS         = { Gold100, Gold200, Gold300, Gold400, Gold500, Gold600, Gold700 };

    private static final Color[] LIGHTGREENS   = { LightGreen400, LightGreen100, LightGreen200, LightGreen300, LightGreen500, LightGreen600, LightGreen700 };

    private static final Color[] GREENS        = { Green400, Green500, Green300, Green100, Green200, Green600, Green700 };

    private static final Color[] CYANS         = { Cyan400, Cyan100, Cyan200, Cyan300, Cyan500, Cyan600, Cyan700 };

    private static final Color[] LIGHTBLUES    = { LightBlue400, LightBlue100, LightBlue200, LightBlue300, LightBlue500, LightBlue600, LightBlue700 };

    private static final Color[] BLUES         = { Blue400, Blue300, Blue100, Blue50, Blue200, Blue500, Blue600, Blue700 };

    private static final Color[] BLACKS        = { Black, Black100, Black150, Black200, Black300, Black400, Black500, Black600, Black700, Black800, Black900 };

    private static final Color[] REDS          = { Red100, Red200, Red300, Red400, Red500 };

    private static final Color[] PURPLE        = { Purple400, Purple100, Purple200, Purple300, Purple500, Purple600, Purple700 };

    class ColorGroup
    {
        private int           m_colorIndex;

        private final Color[] m_colors;

        public ColorGroup(Color[] colors)
        {
            m_colors = colors;
        }

        public IColor getNext()
        {
            if (m_colorIndex >= m_colors.length)
            {
                m_colorIndex = 0;
            }

            Color color = m_colors[m_colorIndex];

            m_colorIndex++;

            return color;
        }
    }
}