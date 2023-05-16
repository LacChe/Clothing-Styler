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
    class Furniture : Item
    {


        public Furniture(Vector2 mPos, string n, int map)
        {
            className = "furniture";
            name = n;
            atMap = map;
            mapPos = mPos;
            stackable = false;
            stackCount = 1;
            maxCount = 1;
            smallItem = false;
            if (name == "testFurniture0")
            {
                sheetName = "furniture";
                spriteSrcRect = new Rectangle(128, 0, 192, 128);
                collideRect = new Rectangle(0, 0, 192, 96);
                spriteSrcRectAlt = new Rectangle(0, 0, 128, 192);
                collideRectAlt = new Rectangle(0, 0, 128, 160);
                collideFromSpriteOffset = new Vector2(0, 32);
                collideFromSpriteOffsetAlt = new Vector2(0, 32);
                button = new Button("furnitureClickArea", spriteSrcRect);
            }
            if (name == "testStool0")
            {
                sheetName = "furniture";
                spriteSrcRect = new Rectangle(0, 192, 32, 32);
                collideRect = new Rectangle(0, 0, 32, 14);
                collideFromSpriteOffset = new Vector2(0, 18);
                button = new Button("furnitureClickArea", spriteSrcRect);
            }
            if (name == "chest")
            {
                sheetName = "chest";
                spriteSrcRect = new Rectangle(0, 0, 64, 64);
                collideRect = new Rectangle(0, 0, 64, 32);
                collideFromSpriteOffset = new Vector2(0, 32);
                button = new Button("chestClicked", spriteSrcRect);
            }
            button.UIbutton = false;
        }

        public override void draw(SpriteBatch spriteBatch)
        {
            if (placeDir == 0) {
                float depth = (mapPos.Y + spriteSrcRect.Height / 2) / (Map.Height(atMap) * Map.TILE_HEIGHT);
                Camera.Draw(spriteBatch, SpriteSheet.GetSheet(sheetName), new Vector2(mapPos.X - collideFromSpriteOffset.X, mapPos.Y - collideFromSpriteOffset.Y), spriteSrcRect, Color.White, 0f, 1.0f, false, depth, 1.0f);
            }

            if (placeDir == 1)
            {
                float depth = (mapPos.Y + spriteSrcRectAlt.Height / 2) / (Map.Height(atMap) * Map.TILE_HEIGHT);
                Camera.Draw(spriteBatch, SpriteSheet.GetSheet(sheetName), new Vector2(mapPos.X - collideFromSpriteOffset.X, mapPos.Y - collideFromSpriteOffset.Y), spriteSrcRectAlt, Color.White, 0f, 1.0f, false, depth, 1.0f);
            }
        }

        public virtual void changeDir()
        {
            if(placeDir == 0)
            {
                placeDir = 1;
            }
            else
            {
                placeDir = 0;
            }
        }

        public override Vector2 getCollideFromSpriteOffset()
        {
            if (placeDir == 0)
            {
                return collideFromSpriteOffset;
            }
            else
            {
                return collideFromSpriteOffsetAlt;
            }
        }

        public override Rectangle getCollideRect()
        {
            Rectangle col;
            if (placeDir == 0)
            {
                col = collideRect;
            }
            else
            {
                col = collideRectAlt;
            }
            col.X += (int)mapPos.X;
            col.Y += (int)mapPos.Y;

            return col;
        }

        public override Rectangle getSpriteSrcRect()
        {
            if (placeDir == 0)
            {
                return spriteSrcRect;
            }
            else
            {
                return spriteSrcRectAlt;
            }
        }
    }
}