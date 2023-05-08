using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Microsoft.Xna.Framework;
using Microsoft.Xna.Framework.Content;
using Microsoft.Xna.Framework.Graphics;

namespace vet
{
    class Camera
    {
        public static Vector2 cameraOffset;

        // temp
        static Crop a = new Crop(new Vector2(0, 0), "apple", 8);

        public static void Draw(SpriteBatch spriteBatch, Texture2D texture, Vector2 position, Rectangle src, Color c, float rotate, float scale, bool horizFlip, float depth, float alpha)
        {
            // get camera offset
            cameraOffset = GetOffset();

            Vector2 origin = new Vector2(0, 0);

            if (rotate != 0)
            {
                origin.X += ((float)src.Width * scale) / 2.0f;
                origin.Y += ((float)src.Height * scale) / 2.0f;
                position.X += ((float)src.Width * scale) / 2.0f;
                position.Y += ((float)src.Height * scale) / 2.0f;
            }

            Color drawColor = c;
            if (Player.player.getAtMap() < 9)
            {
                float dayPercent = 0.0f;
                if (Time.getDayPercent() >= 0.5)
                {
                    dayPercent = (2.0f - Time.getDayPercent() * 2);
                }
                else if (Time.getDayPercent() < 0.5)
                {
                    dayPercent = (Time.getDayPercent() * 2);
                }
                dayPercent = dayPercent * 5 - 2;
                if (dayPercent > 1) dayPercent = 1;
                if (dayPercent < 0) dayPercent = 0;
                Color fadeColor = new Color((int)(255.0f * dayPercent), (int)(255.0f * dayPercent), (int)(255.0f * dayPercent));
                drawColor = new Color((c.R + fadeColor.R * 2) / 3, (c.G + fadeColor.G * 2) / 3, (c.B + fadeColor.B * 2) / 3);
            }
        
            if (horizFlip)
            {
                spriteBatch.Draw(texture, new Vector2(-cameraOffset.X + position.X, -cameraOffset.Y + position.Y), //
                    src, drawColor * alpha, rotate, origin, scale, SpriteEffects.FlipHorizontally, depth);
            }
            else
            {
                spriteBatch.Draw(texture, new Vector2(-cameraOffset.X + position.X, -cameraOffset.Y + position.Y), //
                    src, drawColor * alpha, rotate, origin, scale, SpriteEffects.None, depth);
            }
        }

        public static void DrawUI(SpriteBatch spriteBatch, Texture2D texture, Vector2 position, Rectangle src, Color c, float rotate, float scale, bool horizFlip, float depth, float alpha)
        {
            cameraOffset.X = 0;
            cameraOffset.Y = 0;

            Vector2 origin = new Vector2(0, 0);

            if (rotate != 0)
            {
                origin.X += ((float)src.Width * scale) / 2.0f;
                origin.Y += ((float)src.Height * scale) / 2.0f;
                position.X += ((float)src.Width * scale) / 2.0f;
                position.Y += ((float)src.Height * scale) / 2.0f;
            }

            if (horizFlip)
            {
                spriteBatch.Draw(texture, new Vector2(-cameraOffset.X + position.X, -cameraOffset.Y + position.Y), //
                    src, c * alpha, rotate, origin, scale, SpriteEffects.FlipHorizontally, depth);
            }
            else
            {
                spriteBatch.Draw(texture, new Vector2(-cameraOffset.X + position.X, -cameraOffset.Y + position.Y), //
                    src, c * alpha, rotate, origin, scale, SpriteEffects.None, depth);
            }

        }

        public static void DrawUIString(SpriteBatch spriteBatch, SpriteFont font, string s, Vector2 position, Color c, float rotate, float scale, bool horizFlip, float depth, float alpha)
        {
            cameraOffset.X = 0;
            cameraOffset.Y = 0;

            if (horizFlip)
            {
                spriteBatch.DrawString(font, s, position, c * alpha, rotate, new Vector2(0, 0), scale, SpriteEffects.FlipHorizontally, depth);
            }
            else
            {
                spriteBatch.DrawString(font, s, position, c * alpha, rotate, new Vector2(0, 0), scale, SpriteEffects.None, depth);
            }
            
        }

        public static Vector2 GetOffset()
        {
            cameraOffset.X = 0;
            cameraOffset.Y = 0;
            cameraOffset.X = Player.player.standPos.X - (Game1.frameSize.X / 2 - Player.player.standSize.X / 2);
            cameraOffset.Y = Player.player.standPos.Y - (Game1.frameSize.Y / 2 - Player.player.standSize.Y / 2 + Player.player.spriteFromStandOffset.Y);

            int mapW = Map.Width(Player.player.getAtMap()) * Map.TILE_WIDTH;
            int mapH = Map.Height(Player.player.getAtMap()) * Map.TILE_HEIGHT;
            int frameW = (int)Game1.frameSize.X;
            int frameH = (int)Game1.frameSize.Y;

            if (Map.getMap(Player.player.getAtMap()).GetType().Name == "Map")
            {

                if (cameraOffset.X < 0) cameraOffset.X = 0;
                if (cameraOffset.Y < 0) cameraOffset.Y = 0;
                if (cameraOffset.X > (mapW - frameW)) cameraOffset.X = (mapW - frameW);
                if (cameraOffset.Y > (mapH - frameH)) cameraOffset.Y = (mapH - frameH);
            }
            return cameraOffset;
        }
    }
}