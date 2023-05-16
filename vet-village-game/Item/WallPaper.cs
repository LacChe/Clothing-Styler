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
    class WallPaper : Item
    {

        public static WallPaper testWallPaper0 = new WallPaper(new Vector2(0, 0), "testWallPaper0", 9);

        public WallPaper(Vector2 mPos, string n, int map)
        {
            className = "wallPaper";
            name = n;
            atMap = map;
            mapPos = mPos;
            stackable = false;
            stackCount = 1;
            maxCount = 1;
            button = new Button("itemClickArea", spriteSrcRect);
            if (name == "testWallPaper0")
            {
                sheetName = "wallPaper";
                spriteSrcRect = new Rectangle(0, 0, 32, 32);
            }
        }

        public virtual void draw(SpriteBatch spriteBatch, Rectangle r)
        {
            for (int i = r.X; i < r.X + r.Width; i++)
            {
                for (int j = r.Y; j < r.Y + r.Height; j++)
                {
                    Camera.Draw(spriteBatch, SpriteSheet.GetSheet(sheetName), new Vector2(i * Map.TILE_WIDTH, j * Map.TILE_HEIGHT), spriteSrcRect, Color.White, 0f, 1.0f, false, 0.0000004f, 1.0f);
                }
            }
        }
    }
}