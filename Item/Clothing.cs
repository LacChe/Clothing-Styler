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
    class Clothing : Item
    {
        protected Vector2 wearOffset;

        public virtual void draw(SpriteBatch spriteBatch, Vector2 pos, bool isMoving, int facingDir, int animIndex, float d)
        {
            className = "clothing";
            pos += wearOffset;
            bool horizFlip = false;

            Rectangle spriteSrc = spriteSrcRect;
            spriteSrc.X += (int)spriteSheetOffset.X;
            spriteSrc.Y += (int)spriteSheetOffset.Y;


            if (facingDir == 1) spriteSrc.Y += 1 * spriteSrcRect.Height;
            if (facingDir == 0) ;
            if (facingDir == 2) spriteSrc.Y += 2 * spriteSrcRect.Height;
            if (facingDir == 3)
            {
                spriteSrc.Y += 2 * spriteSrcRect.Height;
                horizFlip = true;
            }

            if (isMoving) 
            {
                if (animIndex == 0) ;
                if (animIndex == 1)
                {
                    pos.Y += 3;
                    spriteSrc.X += 1 * spriteSrcRect.Width;
                }
                if (animIndex == 2) ;
                if (animIndex == 3) 
                {
                    pos.Y += 3;
                    spriteSrc.X += 2 * spriteSrcRect.Width; 
                }
            }

            Camera.Draw(spriteBatch, SpriteSheet.GetSheet(sheetName), pos, spriteSrc, Color.White, 0f, 1.0f, horizFlip, d + 0.0000001f, 1.0f);
        }


    }
}