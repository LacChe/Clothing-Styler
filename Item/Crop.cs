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
    class Crop : Item
    {

        public int hp = 500;


        public Crop(Vector2 mPos, string n, int map)
        {
            className = "crop";
            name = n;
            atMap = map;
            mapPos = mPos;
            stackable = true;
            stackCount = 1;
            maxCount = 65;
            button = new Button("cropClickArea", spriteSrcRect);
            sheetName = "crops";
            spriteSrcRect = new Rectangle(0, 0, 32, 32);
            if (name == "peas")
            {
                spriteSheetOffset = new Vector2(0, 0 * 32);
                collideRect = new Rectangle(0, 0, 16, 16);
                collideFromSpriteOffset = new Vector2(8, 8);
            }
            if (name == "driedSoybeans")
            {
                spriteSheetOffset = new Vector2(0, 1 * 32);
                collideRect = new Rectangle(0, 0, 16, 16);
                collideFromSpriteOffset = new Vector2(8, 8);
            }
            if (name == "soybeans")
            {
                spriteSheetOffset = new Vector2(0, 2 * 32);
                collideRect = new Rectangle(0, 0, 16, 16);
                collideFromSpriteOffset = new Vector2(8, 8);
            }
            if (name == "sunflowerLeaves")
            {
                spriteSheetOffset = new Vector2(0, 3 * 32);
                collideRect = new Rectangle(0, 0, 32, 32);
                collideFromSpriteOffset = new Vector2(0, 0);
            }
            if (name == "shepherdsPurse")
            {
                spriteSheetOffset = new Vector2(0, 4 * 32);
                collideRect = new Rectangle(0, 0, 16, 16);
                collideFromSpriteOffset = new Vector2(8, 8);
            }
            if (name == "celtuce")
            {
                spriteSheetOffset = new Vector2(0, 5 * 32);
                collideRect = new Rectangle(0, 0, 32, 32);
                collideFromSpriteOffset = new Vector2(0, 0);
            }
            if (name == "rice")
            {
                spriteSheetOffset = new Vector2(0, 6 * 32);
                collideRect = new Rectangle(0, 0, 16, 16);
                collideFromSpriteOffset = new Vector2(8, 8);
            }
            if (name == "chestnuts")
            {
                spriteSheetOffset = new Vector2(0, 7 * 32);
                collideRect = new Rectangle(0, 0, 16, 16);
                collideFromSpriteOffset = new Vector2(8, 8);
            }
            if (name == "wheat")
            {
                spriteSheetOffset = new Vector2(0, 8 * 32);
                collideRect = new Rectangle(0, 0, 16, 32);
                collideFromSpriteOffset = new Vector2(8, 0);
            }
            if (name == "hawthorn")
            {
                spriteSheetOffset = new Vector2(0, 9 * 32);
                collideRect = new Rectangle(0, 0, 16, 16);
                collideFromSpriteOffset = new Vector2(8, 8);
            }
            if (name == "celery")
            {
                spriteSheetOffset = new Vector2(0, 10 * 32);
                collideRect = new Rectangle(0, 0, 16, 32);
                collideFromSpriteOffset = new Vector2(8, 0);
            }
            if (name == "cabbage")
            {
                spriteSheetOffset = new Vector2(0, 11 * 32);
                collideRect = new Rectangle(0, 0, 32, 32);
                collideFromSpriteOffset = new Vector2(0, 0);
            }
            if (name == "dates")
            {
                spriteSheetOffset = new Vector2(0, 12 * 32);
                collideRect = new Rectangle(0, 0, 16, 16);
                collideFromSpriteOffset = new Vector2(8, 8);
            }
            if (name == "plum")
            {
                spriteSheetOffset = new Vector2(0, 13 * 32);
                collideRect = new Rectangle(0, 0, 16, 16);
                collideFromSpriteOffset = new Vector2(8, 8);
            }
            if (name == "apricots")
            {
                spriteSheetOffset = new Vector2(0, 14 * 32);
                collideRect = new Rectangle(0, 0, 16, 16);
                collideFromSpriteOffset = new Vector2(8, 8);
            }
            if (name == "scallions")
            {
                spriteSheetOffset = new Vector2(0, 15 * 32);
                collideRect = new Rectangle(0, 0, 16, 32);
                collideFromSpriteOffset = new Vector2(8, 0);
            }
            if (name == "ginger")
            {
                spriteSheetOffset = new Vector2(0, 16 * 32);
                collideRect = new Rectangle(0, 0, 16, 16);
                collideFromSpriteOffset = new Vector2(8, 8);
            }
            if (name == "grapes")
            {
                spriteSheetOffset = new Vector2(0, 17 * 32);
                collideRect = new Rectangle(0, 0, 32, 32);
                collideFromSpriteOffset = new Vector2(0, 0);
            }
            if (name == "pomegranate")
            {
                spriteSheetOffset = new Vector2(0, 18 * 32);
                collideRect = new Rectangle(0, 0, 16, 16);
                collideFromSpriteOffset = new Vector2(8, 8);
            }
            if (name == "lychee")
            {
                spriteSheetOffset = new Vector2(0, 19 * 32);
                collideRect = new Rectangle(0, 0, 16, 16);
                collideFromSpriteOffset = new Vector2(8, 8);
            }
            if (name == "strawberry")
            {
                spriteSheetOffset = new Vector2(0, 20 * 32);
                collideRect = new Rectangle(0, 0, 10, 12);
                collideFromSpriteOffset = new Vector2(11, 10);
            }
            if (name == "garlic")
            {
                spriteSheetOffset = new Vector2(0, 21 * 32);
                collideRect = new Rectangle(0, 0, 16, 16);
                collideFromSpriteOffset = new Vector2(8, 8);
            }
            if (name == "honey")
            {
                spriteSheetOffset = new Vector2(0, 22 * 32);
                collideRect = new Rectangle(0, 0, 16, 16);
                collideFromSpriteOffset = new Vector2(8, 8);
            }
            if (name == "sesame")
            {
                spriteSheetOffset = new Vector2(0, 23 * 32);
                collideRect = new Rectangle(0, 0, 16, 16);
                collideFromSpriteOffset = new Vector2(8, 8);
            }
            if (name == "radish")
            {
                spriteSheetOffset = new Vector2(0, 24 * 32);
                collideRect = new Rectangle(0, 0, 16, 32);
                collideFromSpriteOffset = new Vector2(8, 0);
            }
            if (name == "spinach")
            {
                spriteSheetOffset = new Vector2(0, 25 * 32);
                collideRect = new Rectangle(0, 0, 32, 32);
                collideFromSpriteOffset = new Vector2(0, 0);
            }
            if (name == "cucumber")
            {
                spriteSheetOffset = new Vector2(0, 26 * 32);
                collideRect = new Rectangle(0, 0, 16, 32);
                collideFromSpriteOffset = new Vector2(8, 0);
            }
            if (name == "eggplant")
            {
                spriteSheetOffset = new Vector2(0, 27 * 32);
                collideRect = new Rectangle(0, 0, 16, 32);
                collideFromSpriteOffset = new Vector2(8, 0);
            }
            if (name == "winterMelon")
            {
                spriteSheetOffset = new Vector2(0, 28 * 32);
                collideRect = new Rectangle(0, 0, 32, 32);
                collideFromSpriteOffset = new Vector2(0, 0);
            }
            if (name == "apple")
            {
                spriteSheetOffset = new Vector2(0, 29 * 32);
                collideRect = new Rectangle(0, 0, 16, 16);
                collideFromSpriteOffset = new Vector2(8, 8);
            }
            if (name == "banana")
            {
                spriteSheetOffset = new Vector2(0, 30 * 32);
                collideRect = new Rectangle(0, 0, 16, 16);
                collideFromSpriteOffset = new Vector2(8, 8);
            }
            if (name == "orange")
            {
                spriteSheetOffset = new Vector2(0, 31 * 32);
                collideRect = new Rectangle(0, 0, 16, 16);
                collideFromSpriteOffset = new Vector2(8, 8);
            }
            if (name == "pepper")
            {
                spriteSheetOffset = new Vector2(0, 32 * 32);
                collideRect = new Rectangle(0, 0, 16, 16);
                collideFromSpriteOffset = new Vector2(8, 8);
            }
            if (name == "tomato")
            {
                spriteSheetOffset = new Vector2(0, 33 * 32);
                collideRect = new Rectangle(0, 0, 16, 16);
                collideFromSpriteOffset = new Vector2(8, 8);
            }
            if (name == "watermelon")
            {
                spriteSheetOffset = new Vector2(0, 34 * 32);
                collideRect = new Rectangle(0, 0, 32, 32);
                collideFromSpriteOffset = new Vector2(0, 0);
            }
            if (name == "sweetPotato")
            {
                spriteSheetOffset = new Vector2(0, 35 * 32);
                collideRect = new Rectangle(0, 0, 16, 16);
                collideFromSpriteOffset = new Vector2(8, 8);
            }
            if (name == "pear")
            {
                spriteSheetOffset = new Vector2(0, 36 * 32);
                collideRect = new Rectangle(0, 0, 16, 16);
                collideFromSpriteOffset = new Vector2(8, 8);
            }
            if (name == "onion")
            {
                spriteSheetOffset = new Vector2(0, 37 * 32);
                collideRect = new Rectangle(0, 0, 16, 16);
                collideFromSpriteOffset = new Vector2(8, 8);
            }
            if (name == "durian")
            {
                spriteSheetOffset = new Vector2(0, 38 * 32);
                collideRect = new Rectangle(0, 0, 32, 26);
                collideFromSpriteOffset = new Vector2(0, 6);
            }
            if (name == "gourd")
            {
                spriteSheetOffset = new Vector2(0, 39 * 32);
                collideRect = new Rectangle(0, 0, 16, 32);
                collideFromSpriteOffset = new Vector2(8, 0);
            }
            if (name == "lotusRoot")
            {
                spriteSheetOffset = new Vector2(0, 40 * 32);
                collideRect = new Rectangle(0, 0, 16, 32);
                collideFromSpriteOffset = new Vector2(8, 0);
            }
            if (name == "lotus")
            {
                spriteSheetOffset = new Vector2(0, 41 * 32);
                collideRect = new Rectangle(0, 0, 16, 16);
                collideFromSpriteOffset = new Vector2(8, 8);
            }
            if (name == "grapefruit")
            {
                spriteSheetOffset = new Vector2(0, 42 * 32);
                collideRect = new Rectangle(0, 0, 32, 32);
                collideFromSpriteOffset = new Vector2(0, 0);
            }
            if (name == "peach")
            {
                spriteSheetOffset = new Vector2(0, 43 * 32);
                collideRect = new Rectangle(0, 0, 16, 16);
                collideFromSpriteOffset = new Vector2(8, 8);
            }
            if (name == "dragonFruit")
            {
                spriteSheetOffset = new Vector2(0, 44 * 32);
                collideRect = new Rectangle(0, 0, 16, 16);
                collideFromSpriteOffset = new Vector2(8, 8);
            }
            if (name == "cantaloupe")
            {
                spriteSheetOffset = new Vector2(0, 45 * 32);
                collideRect = new Rectangle(0, 0, 32, 32);
                collideFromSpriteOffset = new Vector2(0, 0);
            }
            if (name == "pumpkin")
            {
                spriteSheetOffset = new Vector2(0, 46 * 32);
                collideRect = new Rectangle(0, 0, 32, 32);
                collideFromSpriteOffset = new Vector2(0, 0);
            }
            if (name == "potato")
            {
                spriteSheetOffset = new Vector2(0, 47 * 32);
                collideRect = new Rectangle(0, 0, 16, 16);
                collideFromSpriteOffset = new Vector2(8, 8);
            }
            if (name == "winterSquash")
            {
                spriteSheetOffset = new Vector2(0, 48 * 32);
                collideRect = new Rectangle(0, 0, 16, 32);
                collideFromSpriteOffset = new Vector2(8, 0);
            }
            if (name == "corn")
            {
                spriteSheetOffset = new Vector2(0, 49 * 32);
                collideRect = new Rectangle(0, 0, 16, 32);
                collideFromSpriteOffset = new Vector2(8, 0);
            }
            if (name == "whiteMushrooms")
            {
                spriteSheetOffset = new Vector2(0, 50 * 32);
                collideRect = new Rectangle(0, 0, 16, 16);
                collideFromSpriteOffset = new Vector2(8, 8);
            }
            if (name == "goldenMushrooms")
            {
                spriteSheetOffset = new Vector2(0, 51 * 32);
                collideRect = new Rectangle(0, 0, 24, 24);
                collideFromSpriteOffset = new Vector2(4, 4);
            }
            if (name == "shiitakeMushrooms")
            {
                spriteSheetOffset = new Vector2(0, 52 * 32);
                collideRect = new Rectangle(0, 0, 16, 16);
                collideFromSpriteOffset = new Vector2(8, 8);
            }
            if (name == "chives")
            {
                spriteSheetOffset = new Vector2(0, 53 * 32);
                collideRect = new Rectangle(0, 0, 16, 32);
                collideFromSpriteOffset = new Vector2(8, 0);
            }
            if (name == "wormwood")
            {
                spriteSheetOffset = new Vector2(0, 54 * 32);
                collideRect = new Rectangle(0, 0, 16, 32);
                collideFromSpriteOffset = new Vector2(8, 0);
            }
            if (name == "bitterMelon")
            {
                spriteSheetOffset = new Vector2(0, 55 * 32);
                collideRect = new Rectangle(0, 0, 16, 32);
                collideFromSpriteOffset = new Vector2(8, 0);
            }
            if (name == "bambooShoots")
            {
                spriteSheetOffset = new Vector2(0, 56 * 32);
                collideRect = new Rectangle(0, 0, 16, 32);
                collideFromSpriteOffset = new Vector2(8, 0);
            }
            if (name == "waterSpinach")
            {
                spriteSheetOffset = new Vector2(0, 57 * 32);
                collideRect = new Rectangle(0, 0, 16, 32);
                collideFromSpriteOffset = new Vector2(8, 0);
            }
            if (name == "sugarcane")
            {
                spriteSheetOffset = new Vector2(0, 58 * 32);
                collideRect = new Rectangle(0, 0, 16, 32);
                collideFromSpriteOffset = new Vector2(8, 0);
            }
            if (name == "konjac")
            {
                spriteSheetOffset = new Vector2(0, 59 * 32);
                collideRect = new Rectangle(0, 0, 16, 16);
                collideFromSpriteOffset = new Vector2(8, 8);
            }
        }
    }
}