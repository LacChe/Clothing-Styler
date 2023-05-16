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
    class Chest : Furniture
    {

        public ChestInventory inventory;

        public Chest(Vector2 mPos, int map) : base (mPos, "chest", map)
        {
            className = "chest";
            inventory = new ChestInventory("chestInventory");
        }

        public override void update(GameTime gameTime)
        {
            if (!inventory.opened())
            {
                button.update(gameTime, this);
            }
            else if (inventory.opened())
            {
                inventory.update(gameTime);
            }
        }

        public override void draw(SpriteBatch spriteBatch)
        {
            Rectangle spriteSrc = spriteSrcRect;
            spriteSrc.X += (int)spriteSheetOffset.X;
            spriteSrc.Y += (int)spriteSheetOffset.Y;
            float depth = (mapPos.Y + spriteSrcRect.Height / 2) / (Map.Height(atMap) * Map.TILE_HEIGHT);
            Camera.Draw(spriteBatch, SpriteSheet.GetSheet(sheetName), new Vector2(mapPos.X - collideFromSpriteOffset.X, mapPos.Y - collideFromSpriteOffset.Y), spriteSrc, Color.White, 0f, 1.0f, false, depth, 1.0f);

            if (inventory.opened())
            {
                inventory.draw(spriteBatch);
            }
        }

        public override void changeDir()
        {

        }
    }
}