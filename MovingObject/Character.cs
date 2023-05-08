using System;
using System.Collections;
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
    class Character : MovingObject
    {

        // stats
        public float hpMax = 1000, hp = 1000;
        public float energyMax = 1000, energy = 1000;
        public float hungerMax = 1000, hunger = 1000;
        public float happinessMax = 1000, happiness = 1000;
        public float familiarityMax = 1000, familiarity = 1000;
        public float sicknessMax = 1000, sickness = 1000, sicknessType = 0;
        protected Dictionary<string, Status> statuses = new Dictionary<string, Status>();

        protected UpperClothing upperClothing = null;


        public override void draw(SpriteBatch spriteBatch)
        {
            if (atMap == Player.player.atMap)
            {
                float depth = (standPos.Y + standSize.Y / 2) / (Map.Height(atMap) * Map.TILE_HEIGHT);
                if (spriteSize != spriteSizeAlt) depth = (standPos.Y + standSize.Y * 0.85f) / (Map.Height(atMap) * Map.TILE_HEIGHT);

                anims[currentAnim].draw(spriteBatch, spritePos, 0f, depth);
                if (upperClothing != null) upperClothing.draw(spriteBatch, spritePos, moving, facingDir, anims[currentAnim].getIndex(), depth);

                // draw statuses
                if(statuses.Count > 0)
                {
                    if (facingDir == 3 || facingDir == 2)
                    {
                        Status.Draw(spriteBatch, statuses, new Vector2(spritePos.X + spriteSize.X / 2, spritePos.Y));
                    }
                    else if (facingDir == 1 || facingDir == 0)
                    {
                        Status.Draw(spriteBatch, statuses, new Vector2(spritePos.X + spriteSizeAlt.X / 2, spritePos.Y));
                    }
                }
            }
        }

        public void setUpperClothing(UpperClothing c)
        {
            upperClothing = c;
        }

        public UpperClothing getUpperClothing()
        {
            return upperClothing;
        }

        protected virtual void updateStatuses(GameTime gameTime)
        {

        }

    }
}
