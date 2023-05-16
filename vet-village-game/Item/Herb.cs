using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Microsoft.Xna.Framework;
using Microsoft.Xna.Framework.Content;
using Microsoft.Xna.Framework.Graphics;
using Microsoft.Xna.Framework.Input;

namespace vet
{
    class Herb : Item
    {


        public Herb(Vector2 mPos, string n, int map)
        {
            className = "herb";
            name = n;
            atMap = map;
            mapPos = mPos;
            stackable = true;
            stackCount = 1;
            maxCount = 65;
            button = new Button("herbClickArea", spriteSrcRect);
            sheetName = "herbs";
            spriteSrcRect = new Rectangle(0, 0, 32, 32);
            if (name == "sageRoot")
            {
                spriteSheetOffset = new Vector2(0, 0 * 32);
                collideRect = new Rectangle(0, 0, 32, 32);
                collideFromSpriteOffset = new Vector2(0, 0);
            }
            if (name == "frankincense")
            {
                spriteSheetOffset = new Vector2(0, 1 * 32);
                collideRect = new Rectangle(0, 0, 16, 16);
                collideFromSpriteOffset = new Vector2(8, 8);
            }
            if (name == "ginseng")
            {
                spriteSheetOffset = new Vector2(0, 2 * 32);
                collideRect = new Rectangle(0, 0, 16, 16);
                collideFromSpriteOffset = new Vector2(8, 8);
            }
            if (name == "hairyveinAgrimonia")
            {
                spriteSheetOffset = new Vector2(0, 3 * 32);
                collideRect = new Rectangle(0, 0, 16, 16);
                collideFromSpriteOffset = new Vector2(8, 8);
            }
            if (name == "fleeceflower")
            {
                spriteSheetOffset = new Vector2(0, 4 * 32);
                collideRect = new Rectangle(0, 0, 16, 16);
                collideFromSpriteOffset = new Vector2(8, 8);
            }
            if (name == "corydalis")
            {
                spriteSheetOffset = new Vector2(0, 5 * 32);
                collideRect = new Rectangle(0, 0, 32, 32);
                collideFromSpriteOffset = new Vector2(0, 0);
            }
            if (name == "piloseAsiabell")
            {
                spriteSheetOffset = new Vector2(0, 6 * 32);
                collideRect = new Rectangle(0, 0, 16, 32);
                collideFromSpriteOffset = new Vector2(8, 0);
            }
            if (name == "chineseCaterpillarFungus")
            {
                spriteSheetOffset = new Vector2(0, 7 * 32);
                collideRect = new Rectangle(0, 0, 16, 16);
                collideFromSpriteOffset = new Vector2(8, 8);
            }
            if (name == "cassiaSeed")
            {
                spriteSheetOffset = new Vector2(0, 8 * 32);
                collideRect = new Rectangle(0, 0, 16, 16);
                collideFromSpriteOffset = new Vector2(8, 8);
            }
            if (name == "manyprickleAcanthopanax")
            {
                spriteSheetOffset = new Vector2(0, 9 * 32);
                collideRect = new Rectangle(0, 0, 16, 16);
                collideFromSpriteOffset = new Vector2(8, 8);
            }
            if (name == "ragwort")
            {
                spriteSheetOffset = new Vector2(0, 10 * 32);
                collideRect = new Rectangle(0, 0, 32, 32);
                collideFromSpriteOffset = new Vector2(0, 0);
            }
            if (name == "pinellia")
            {
                spriteSheetOffset = new Vector2(0, 11 * 32);
                collideRect = new Rectangle(0, 0, 16, 32);
                collideFromSpriteOffset = new Vector2(8, 0);
            }
            if (name == "tamariskoidSpikemoss")
            {
                spriteSheetOffset = new Vector2(0, 12 * 32);
                collideRect = new Rectangle(0, 0, 32, 32);
                collideFromSpriteOffset = new Vector2(0, 0);
            }
            if (name == "magnoliaBark")
            {
                spriteSheetOffset = new Vector2(0, 13 * 32);
                collideRect = new Rectangle(0, 0, 16, 16);
                collideFromSpriteOffset = new Vector2(8, 8);
            }
            if (name == "rehmanniaRoot")
            {
                spriteSheetOffset = new Vector2(0, 14 * 32);
                collideRect = new Rectangle(0, 0, 32, 32);
                collideFromSpriteOffset = new Vector2(0, 0);
            }
            if (name == "earthworm")
            {
                spriteSheetOffset = new Vector2(0, 15 * 32);
                collideRect = new Rectangle(0, 0, 16, 16);
                collideFromSpriteOffset = new Vector2(8, 8);
            }
            if (name == "stringyStonecrop")
            {
                spriteSheetOffset = new Vector2(0, 16 * 32);
                collideRect = new Rectangle(0, 0, 16, 16);
                collideFromSpriteOffset = new Vector2(8, 8);
            }
            if (name == "selfheal")
            {
                spriteSheetOffset = new Vector2(0, 17 * 32);
                collideRect = new Rectangle(0, 0, 32, 32);
                collideFromSpriteOffset = new Vector2(0, 0);
            }
            if (name == "sorrelRhubarb")
            {
                spriteSheetOffset = new Vector2(0, 18 * 32);
                collideRect = new Rectangle(0, 0, 32, 32);
                collideFromSpriteOffset = new Vector2(0, 0);
            }
            if (name == "tallGastrodia")
            {
                spriteSheetOffset = new Vector2(0, 19 * 32);
                collideRect = new Rectangle(0, 0, 16, 16);
                collideFromSpriteOffset = new Vector2(8, 8);
            }
            if (name == "glossyFrivetFruit")
            {
                spriteSheetOffset = new Vector2(0, 20 * 32);
                collideRect = new Rectangle(0, 0, 16, 16);
                collideFromSpriteOffset = new Vector2(8, 8);
            }
            if (name == "fieldThistle")
            {
                spriteSheetOffset = new Vector2(0, 21 * 32);
                collideRect = new Rectangle(0, 0, 32, 32);
                collideFromSpriteOffset = new Vector2(0, 0);
            }
            if (name == "szechuanLovageRoot")
            {
                spriteSheetOffset = new Vector2(0, 22 * 32);
                collideRect = new Rectangle(0, 0, 16, 16);
                collideFromSpriteOffset = new Vector2(8, 8);
            }
            if (name == "chineseAngelicaRoot")
            {
                spriteSheetOffset = new Vector2(0, 23 * 32);
                collideRect = new Rectangle(0, 0, 16, 16);
                collideFromSpriteOffset = new Vector2(8, 8);
            }
            if (name == "eclipta")
            {
                spriteSheetOffset = new Vector2(0, 24 * 32);
                collideRect = new Rectangle(0, 0, 16, 32);
                collideFromSpriteOffset = new Vector2(8, 0);
            }
            if (name == "chineseRose")
            {
                spriteSheetOffset = new Vector2(0, 25 * 32);
                collideRect = new Rectangle(0, 0, 16, 32);
                collideFromSpriteOffset = new Vector2(8, 0);
            }
            if (name == "aucklandiaRoot")
            {
                spriteSheetOffset = new Vector2(0, 26 * 32);
                collideRect = new Rectangle(0, 0, 16, 16);
                collideFromSpriteOffset = new Vector2(8, 8);
            }
            if (name == "cinnabar")
            {
                spriteSheetOffset = new Vector2(0, 27 * 32);
                collideRect = new Rectangle(0, 0, 16, 16);
                collideFromSpriteOffset = new Vector2(8, 8);
            }
            if (name == "eucommiaBark")
            {
                spriteSheetOffset = new Vector2(0, 28 * 32);
                collideRect = new Rectangle(0, 0, 16, 16);
                collideFromSpriteOffset = new Vector2(8, 8);
            }
            if (name == "dyersWoadRoot")
            {
                spriteSheetOffset = new Vector2(0, 29 * 32);
                collideRect = new Rectangle(0, 0, 32, 32);
                collideFromSpriteOffset = new Vector2(0, 0);
            }
        }
    }
}