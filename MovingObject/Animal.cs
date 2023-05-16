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
    class Animal : Character
    {

        private bool following = false;
        private int followIndex = -1;

        public Animal(string n)
        {
            statuses.Add("heart", new Status("heart", false));
            statuses.Add("happyFace", new Status("happyFace", false));
            statuses.Add("sadFace", new Status("sadFace", false));
            statuses.Add("angry", new Status("angry", false));
            statuses.Add("sick", new Status("sick", false));
            statuses.Add("vomit", new Status("vomit", false));
            statuses.Add("death", new Status("death", false));
            statuses.Add("hp", new Status("hp", true));
            statuses.Add("hunger", new Status("hunger", true));
            statuses.Add("sleep", new Status("sleep", false));
            statuses.Add("question", new Status("question", false));
            statuses.Add("exclamation", new Status("exclamation", false));
            statuses.Add("movingHearts", new Status("movingHearts", false));

            className = "animal";

            name = n;

            moveAnimSpeed = 10;
            standAnimSpeed = 20;
            runAnimSpeed = 6;
            moveSpeed = 3;

            spriteSize = new Vector2(64, 32);
            standSize = new Vector2(32, 32);
            spriteFromStandOffset = new Vector2(16, 0);

            spriteSizeAlt = new Vector2(32, 64);
            spriteFromStandOffsetAlt = new Vector2(0, 32);

            // temp
            facingDir = 1;
            atMap = 3;
            standPos = new Vector2(2600, 400);

            Rectangle frontStand0 = new Rectangle(0 * (int)spriteSizeAlt.X, 0 * (int)spriteSizeAlt.Y, (int)spriteSizeAlt.X, (int)spriteSizeAlt.Y);
            Rectangle frontStand1 = new Rectangle(1 * (int)spriteSizeAlt.X, 0 * (int)spriteSizeAlt.Y, (int)spriteSizeAlt.X, (int)spriteSizeAlt.Y);
            Rectangle frontStand2 = new Rectangle(2 * (int)spriteSizeAlt.X, 0 * (int)spriteSizeAlt.Y, (int)spriteSizeAlt.X, (int)spriteSizeAlt.Y);
            Rectangle frontStand3 = new Rectangle(3 * (int)spriteSizeAlt.X, 0 * (int)spriteSizeAlt.Y, (int)spriteSizeAlt.X, (int)spriteSizeAlt.Y);
            Rectangle frontStand4 = new Rectangle(4 * (int)spriteSizeAlt.X, 0 * (int)spriteSizeAlt.Y, (int)spriteSizeAlt.X, (int)spriteSizeAlt.Y);

            Rectangle backStand0 = new Rectangle(0 * (int)spriteSizeAlt.X, 1 * (int)spriteSizeAlt.Y, (int)spriteSizeAlt.X, (int)spriteSizeAlt.Y);
            Rectangle backStand1 = new Rectangle(1 * (int)spriteSizeAlt.X, 1 * (int)spriteSizeAlt.Y, (int)spriteSizeAlt.X, (int)spriteSizeAlt.Y);
            Rectangle backStand2 = new Rectangle(2 * (int)spriteSizeAlt.X, 1 * (int)spriteSizeAlt.Y, (int)spriteSizeAlt.X, (int)spriteSizeAlt.Y);
            Rectangle backStand3 = new Rectangle(3 * (int)spriteSizeAlt.X, 1 * (int)spriteSizeAlt.Y, (int)spriteSizeAlt.X, (int)spriteSizeAlt.Y);

            Rectangle sideStand0 = new Rectangle(0 * (int)spriteSize.X, 4 * (int)spriteSize.Y, (int)spriteSize.X, (int)spriteSize.Y);
            Rectangle sideStand1 = new Rectangle(1 * (int)spriteSize.X, 4 * (int)spriteSize.Y, (int)spriteSize.X, (int)spriteSize.Y);
            Rectangle sideStand2 = new Rectangle(2 * (int)spriteSize.X, 4 * (int)spriteSize.Y, (int)spriteSize.X, (int)spriteSize.Y);
            Rectangle sideStand3 = new Rectangle(3 * (int)spriteSize.X, 4 * (int)spriteSize.Y, (int)spriteSize.X, (int)spriteSize.Y);
            Rectangle sideStand4 = new Rectangle(4 * (int)spriteSize.X, 4 * (int)spriteSize.Y, (int)spriteSize.X, (int)spriteSize.Y);

            Rectangle frontWalk0 = new Rectangle(0 * (int)spriteSizeAlt.X, (int)(2.5 * spriteSizeAlt.Y), (int)spriteSizeAlt.X, (int)spriteSizeAlt.Y);
            Rectangle frontWalk1 = new Rectangle(1 * (int)spriteSizeAlt.X, (int)(2.5 * spriteSizeAlt.Y), (int)spriteSizeAlt.X, (int)spriteSizeAlt.Y);
            Rectangle frontWalk2 = new Rectangle(2 * (int)spriteSizeAlt.X, (int)(2.5 * spriteSizeAlt.Y), (int)spriteSizeAlt.X, (int)spriteSizeAlt.Y);
            Rectangle frontWalk3 = new Rectangle(3 * (int)spriteSizeAlt.X, (int)(2.5 * spriteSizeAlt.Y), (int)spriteSizeAlt.X, (int)spriteSizeAlt.Y);

            Rectangle backWalk0 = new Rectangle(0 * (int)spriteSizeAlt.X, (int)(3.5 * spriteSizeAlt.Y), (int)spriteSizeAlt.X, (int)spriteSizeAlt.Y);
            Rectangle backWalk1 = new Rectangle(1 * (int)spriteSizeAlt.X, (int)(3.5 * spriteSizeAlt.Y), (int)spriteSizeAlt.X, (int)spriteSizeAlt.Y);
            Rectangle backWalk2 = new Rectangle(2 * (int)spriteSizeAlt.X, (int)(3.5 * spriteSizeAlt.Y), (int)spriteSizeAlt.X, (int)spriteSizeAlt.Y);
            Rectangle backWalk3 = new Rectangle(3 * (int)spriteSizeAlt.X, (int)(3.5 * spriteSizeAlt.Y), (int)spriteSizeAlt.X, (int)spriteSizeAlt.Y);

            Rectangle sideWalk0 = new Rectangle(0 * (int)spriteSize.X, 9 * (int)spriteSize.Y, (int)spriteSize.X, (int)spriteSize.Y);
            Rectangle sideWalk1 = new Rectangle(1 * (int)spriteSize.X, 9 * (int)spriteSize.Y, (int)spriteSize.X, (int)spriteSize.Y);
            Rectangle sideWalk2 = new Rectangle(2 * (int)spriteSize.X, 9 * (int)spriteSize.Y, (int)spriteSize.X, (int)spriteSize.Y);
            Rectangle sideWalk3 = new Rectangle(3 * (int)spriteSize.X, 9 * (int)spriteSize.Y, (int)spriteSize.X, (int)spriteSize.Y);

            Rectangle frontRun0 = new Rectangle(0 * (int)spriteSizeAlt.X, 5 * (int)spriteSizeAlt.Y, (int)spriteSizeAlt.X, (int)spriteSizeAlt.Y);
            Rectangle frontRun1 = new Rectangle(1 * (int)spriteSizeAlt.X, 5 * (int)spriteSizeAlt.Y, (int)spriteSizeAlt.X, (int)spriteSizeAlt.Y);
            Rectangle frontRun2 = new Rectangle(2 * (int)spriteSizeAlt.X, 5 * (int)spriteSizeAlt.Y, (int)spriteSizeAlt.X, (int)spriteSizeAlt.Y);
            Rectangle frontRun3 = new Rectangle(3 * (int)spriteSizeAlt.X, 5 * (int)spriteSizeAlt.Y, (int)spriteSizeAlt.X, (int)spriteSizeAlt.Y);
            // Rectangle frontRun4 = new Rectangle(4 * (int)spriteSizeAlt.X, 5 * (int)spriteSizeAlt.Y, (int)spriteSizeAlt.X, (int)spriteSizeAlt.Y);
            // Rectangle frontRun5 = new Rectangle(5 * (int)spriteSizeAlt.X, 5 * (int)spriteSizeAlt.Y, (int)spriteSizeAlt.X, (int)spriteSizeAlt.Y);
            // Rectangle frontRun6 = new Rectangle(6 * (int)spriteSizeAlt.X, 5 * (int)spriteSizeAlt.Y, (int)spriteSizeAlt.X, (int)spriteSizeAlt.Y);
            // Rectangle frontRun7 = new Rectangle(7 * (int)spriteSizeAlt.X, 5 * (int)spriteSizeAlt.Y, (int)spriteSizeAlt.X, (int)spriteSizeAlt.Y);

            Rectangle backRun0 = new Rectangle(0 * (int)spriteSizeAlt.X, 6 * (int)spriteSizeAlt.Y, (int)spriteSizeAlt.X, (int)spriteSizeAlt.Y);
            Rectangle backRun1 = new Rectangle(1 * (int)spriteSizeAlt.X, 6 * (int)spriteSizeAlt.Y, (int)spriteSizeAlt.X, (int)spriteSizeAlt.Y);
            Rectangle backRun2 = new Rectangle(2 * (int)spriteSizeAlt.X, 6 * (int)spriteSizeAlt.Y, (int)spriteSizeAlt.X, (int)spriteSizeAlt.Y);
            Rectangle backRun3 = new Rectangle(3 * (int)spriteSizeAlt.X, 6 * (int)spriteSizeAlt.Y, (int)spriteSizeAlt.X, (int)spriteSizeAlt.Y);
            // Rectangle backRun4 = new Rectangle(4 * (int)spriteSizeAlt.X, 6 * (int)spriteSizeAlt.Y, (int)spriteSizeAlt.X, (int)spriteSizeAlt.Y);
            // Rectangle backRun5 = new Rectangle(5 * (int)spriteSizeAlt.X, 6 * (int)spriteSizeAlt.Y, (int)spriteSizeAlt.X, (int)spriteSizeAlt.Y);
            // Rectangle backRun6 = new Rectangle(6 * (int)spriteSizeAlt.X, 6 * (int)spriteSizeAlt.Y, (int)spriteSizeAlt.X, (int)spriteSizeAlt.Y);
            // Rectangle backRun7 = new Rectangle(7 * (int)spriteSizeAlt.X, 6 * (int)spriteSizeAlt.Y, (int)spriteSizeAlt.X, (int)spriteSizeAlt.Y);

            Rectangle sideRun0 = new Rectangle(0 * (int)spriteSize.X, 14 * (int)spriteSize.Y, (int)spriteSize.X, (int)spriteSize.Y);
            Rectangle sideRun1 = new Rectangle(1 * (int)spriteSize.X, 14 * (int)spriteSize.Y, (int)spriteSize.X, (int)spriteSize.Y);
            Rectangle sideRun2 = new Rectangle(2 * (int)spriteSize.X, 14 * (int)spriteSize.Y, (int)spriteSize.X, (int)spriteSize.Y);
            Rectangle sideRun3 = new Rectangle(3 * (int)spriteSize.X, 14 * (int)spriteSize.Y, (int)spriteSize.X, (int)spriteSize.Y);
            // Rectangle sideRun4 = new Rectangle(4 * (int)spriteSize.X, 14 * (int)spriteSize.Y, (int)spriteSize.X, (int)spriteSize.Y);
            // Rectangle sideRun5 = new Rectangle(5 * (int)spriteSize.X, 14 * (int)spriteSize.Y, (int)spriteSize.X, (int)spriteSize.Y);
            // Rectangle sideRun6 = new Rectangle(6 * (int)spriteSize.X, 14 * (int)spriteSize.Y, (int)spriteSize.X, (int)spriteSize.Y);
            // Rectangle sideRun7 = new Rectangle(7 * (int)spriteSize.X, 14 * (int)spriteSize.Y, (int)spriteSize.X, (int)spriteSize.Y);

            Rectangle frontSit0 = new Rectangle(0 * (int)spriteSizeAlt.X, (int)(7.5 * spriteSizeAlt.Y), (int)spriteSizeAlt.X, (int)spriteSizeAlt.Y);
            Rectangle frontSit1 = new Rectangle(1 * (int)spriteSizeAlt.X, (int)(7.5 * spriteSizeAlt.Y), (int)spriteSizeAlt.X, (int)spriteSizeAlt.Y);
            Rectangle frontSit2 = new Rectangle(2 * (int)spriteSizeAlt.X, (int)(7.5 * spriteSizeAlt.Y), (int)spriteSizeAlt.X, (int)spriteSizeAlt.Y);
            Rectangle frontSit3 = new Rectangle(3 * (int)spriteSizeAlt.X, (int)(7.5 * spriteSizeAlt.Y), (int)spriteSizeAlt.X, (int)spriteSizeAlt.Y);
            Rectangle frontSit4 = new Rectangle(4 * (int)spriteSizeAlt.X, (int)(7.5 * spriteSizeAlt.Y), (int)spriteSizeAlt.X, (int)spriteSizeAlt.Y);

            anims = new Animation[13];
            anims[0] = new Animation(name, 8, new Rectangle[] { frontStand0, frontStand2, frontStand3, frontStand4, frontStand1, frontStand2, frontStand3, frontStand4 }, standAnimSpeed);
            anims[1] = new Animation(name, 4, new Rectangle[] { backStand0, backStand1, backStand2, backStand3 }, standAnimSpeed);
            anims[2] = new Animation(name, 8, new Rectangle[] { sideStand0, sideStand2, sideStand3, sideStand4, sideStand1, sideStand2, sideStand3, sideStand4 }, standAnimSpeed);
            anims[3] = new Animation(name, 8, new Rectangle[] { sideStand0, sideStand2, sideStand3, sideStand4, sideStand1, sideStand2, sideStand3, sideStand4 }, standAnimSpeed);
            anims[3].setHorizontalFlip();

            anims[4] = new Animation(name, 4, new Rectangle[] { frontWalk0, frontWalk1, frontWalk2, frontWalk3 }, moveAnimSpeed);
            anims[5] = new Animation(name, 4, new Rectangle[] { backWalk0, backWalk1, backWalk2, backWalk3 }, moveAnimSpeed);
            anims[6] = new Animation(name, 4, new Rectangle[] { sideWalk0, sideWalk1, sideWalk2, sideWalk3 }, moveAnimSpeed);
            anims[7] = new Animation(name, 4, new Rectangle[] { sideWalk0, sideWalk1, sideWalk2, sideWalk3 }, moveAnimSpeed);
            anims[7].setHorizontalFlip();

            anims[8] = new Animation(name, 4, new Rectangle[] { frontRun0, frontRun1, frontRun2, frontRun3 }, runAnimSpeed);
            anims[9] = new Animation(name, 4, new Rectangle[] { backRun0, backRun1, backRun2, backRun3 }, runAnimSpeed);
            anims[10] = new Animation(name, 4, new Rectangle[] { sideRun0, sideRun1, sideRun2, sideRun3 }, runAnimSpeed);
            anims[11] = new Animation(name, 4, new Rectangle[] { sideRun0, sideRun1, sideRun2, sideRun3 }, runAnimSpeed);
            anims[11].setHorizontalFlip();

            anims[12] = new Animation(name, 8, new Rectangle[] { frontSit0, frontSit2, frontSit3, frontSit4, frontSit1, frontSit2, frontSit3, frontSit4 }, standAnimSpeed);
        }

        public override void update(GameTime gameTime)
        {
            moving = false;
            running = false;
            sitting = true; // override

            // follow? override
            following = true;
            sitting = false;
            moving = true;

            if (moving || running) move();

            if (clicked()) updateClicked(gameTime);

            updateStatuses(gameTime);

            // anims
            if (facingDir == 3 || facingDir == 2)
            {
                spritePos.X = standPos.X - spriteFromStandOffset.X;
                spritePos.Y = standPos.Y - spriteFromStandOffset.Y;
            }
            else if (facingDir == 1 || facingDir == 0 || sitting)
            {
                spritePos.X = standPos.X - spriteFromStandOffsetAlt.X;
                spritePos.Y = standPos.Y - spriteFromStandOffsetAlt.Y;
            }
            currentAnim = facingDir;
            if (moving) currentAnim += 4;
            if (running) currentAnim += 4;
            if (sitting) currentAnim = 12;
            if (sleeping) ;
            anims[currentAnim].update(gameTime);
        }

        public override void move()
        {

            int oldSpeed = moveSpeed;
            if (running) moveSpeed *= 2;

            int nextX = 0;
            int nextY = 0;

            if (following) badAIFollow(ref nextX, ref nextY);


            for (int i = 0; i < moveSpeed; i++)
            {
                // tiles
                // x
                if (!collideWithMap(nextX, 0))
                {
                    standPos.X += nextX;
                }
                // y
                if (!collideWithMap(0, nextY))
                {
                    standPos.Y += nextY;
                }

                // TODO: stuff that needs to hapen every pixel step
                switchMap();
            }
            moveSpeed = oldSpeed;

            if (nextX == 0 && nextY == 0) moving = false;

            facingDir = 0;
            if (nextX == 0)
            {
                if (nextY == 1) facingDir = 0;
                else if (nextY == -1) facingDir = 1;
            } else
            {
                if (nextX == 1) facingDir = 2;
                else if (nextX == -1) facingDir = 3;
            }
        }

        protected override void updateStatuses(GameTime gameTime)
        {
            // sickness
            sickness = 0;
            if (sickness < 0) sickness = 0;
            if (sickness > sicknessMax) sickness = sicknessMax;

            // hp
            if (hp < 0) hp = 0;
            if (hp > hpMax) hp = hpMax;

            // energy
            if (moving) energy -= 0;
            if (running) energy -= (3000.0f / (float)Time.getDayLength());
            if (sitting) energy += (600.0f / (float)Time.getDayLength());
            if (sleeping) energy += (2500.0f / (float)Time.getDayLength());
            if (energy < 0) energy = 0;
            if (energy > energyMax) energy = energyMax;


            // happiness
            happiness -= (300.0f / (float)Time.getDayLength());
            if (happiness < 0) happiness = 0;
            if (happiness > happinessMax) happiness = happinessMax;

            // familiarity
            if (familiarity < 0) familiarity = 0;
            if (familiarity > familiarityMax) familiarity = familiarityMax;

            // hunger
            hunger -= (600.0f / (float)Time.getDayLength());
            if (hunger < 0) hunger = 0;
            if (hunger > hungerMax) hunger = hungerMax;

            // update status objects
            Status.Update(gameTime, statuses, this);
            // Console.WriteLine("Doggo | Sickness: " + (sickness / sicknessMax) + " | Hunger: " + (hunger / hungerMax) + " | HP: " + (hp/ hpMax) + " | Energy: " + (energy / energyMax) + " | Happiness: " + (happiness/ happinessMax) + " | Familiarity: " + (familiarity / familiarityMax));
        }

        protected override void updateClicked(GameTime gameTime)
        {
            if (Player.player.inventory.getItem(Player.player.inventory.getIndex()) != null && //
                Player.player.inventory.getItem(Player.player.inventory.getIndex()).getClass() == "crop")
            {
                Crop crop = new Crop(new Vector2(0, 0), (Player.player.inventory.removeSingleItem(Player.player.inventory.getIndex()).getName()), 0);
                hp += crop.hp;
                hunger += crop.hp;

                statuses["happyFace"].duration = 60;
                statuses["happyFace"].constant = false;
            }
        }

        protected bool hasDirectPath(Rectangle start, Vector2 end)
        {
            while ((start.X != end.X || start.Y != end.Y) && Vector2.Distance(new Vector2(start.X, start.Y), end) >= 2 * 32)
            {
                if (Map.collideWithMap(atMap, start, false)) return false;
                if (start.X < end.X) start.X += (int)(standSize.X - 1);
                if (start.X > end.X) start.X -= (int)(standSize.X - 1);
                if (start.Y < end.Y) start.Y += (int)(standSize.Y - 1);
                if (start.Y > end.Y) start.Y -= (int)(standSize.Y - 1);
            }
            return true;
        }

        protected void badAIFollow(ref int nextX, ref int nextY)
        {
            Rectangle startPos = new Rectangle((int)standPos.X, (int)standPos.Y, (int)standSize.X, (int)standSize.Y);
            Vector2 endPos;
            if (followIndex == -1) endPos = new Vector2(Player.player.standPos.X + Player.player.standSize.X / 2 - standSize.Y / 2,//
                Player.player.standPos.Y + Player.player.standSize.Y / 2 - standSize.Y / 2);
            else endPos = new Vector2(MovingObject.getInterMapObjects()[followIndex].standPos.X + MovingObject.getInterMapObjects()[followIndex].standSize.X, MovingObject.getInterMapObjects()[followIndex].standPos.Y + MovingObject.getInterMapObjects()[followIndex].standSize.Y);

            if (((followIndex == -1 ? Player.player.getAtMap() : MovingObject.getInterMapObjects()[followIndex].getAtMap()) == atMap) && // in same map
                Vector2.Distance(new Vector2(startPos.X, startPos.Y), endPos) >= 2 * 32 && // has path
                hasDirectPath(startPos, endPos)) // not too close
            {
                if (startPos.X + moveSpeed < endPos.X) nextX = 1;
                else if (startPos.X - moveSpeed > endPos.X) nextX = -1;
                if (startPos.Y + moveSpeed < endPos.Y) nextY = 1;
                else if (startPos.Y - moveSpeed > endPos.Y) nextY = -1;
            }
        }

    }
}