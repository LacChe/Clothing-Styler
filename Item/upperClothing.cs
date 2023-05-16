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
    class UpperClothing : Clothing
    {

        public UpperClothing(Vector2 mPos, string n, int map)
        {
            className = "upperClothing";
            name = n;
            atMap = map;
            mapPos = mPos;
            stackable = false;
            stackCount = 1;
            maxCount = 1;
            button = new Button("itemClickArea", spriteSrcRect);
            if (name == "testUpperClothes0")
            {
                sheetName = "upperClothes";
                spriteSrcRect = new Rectangle(0, 0, 24, 44);
                collideRect = new Rectangle(0, 0, 24, 44);
                wearOffset = new Vector2(20, 43);
            }
            if (name == "testUpperClothes1")
            {
                sheetName = "upperClothes";
                spriteSrcRect = new Rectangle(0, 0, 24, 44);
                spriteSheetOffset = new Vector2(0, 3 * 44);
                collideRect = new Rectangle(0, 0, 24, 44);
                wearOffset = new Vector2(20, 43);
            }
            if (name == "testUpperClothes2")
            {
                sheetName = "upperClothes";
                spriteSrcRect = new Rectangle(0, 0, 24, 44);
                spriteSheetOffset = new Vector2(0, 6 * 44);
                collideRect = new Rectangle(0, 0, 24, 44);
                wearOffset = new Vector2(20, 43);
            }
        }
    }
}