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
    class LiHe : NPC
    {
        // temp
        int length = 500;

        private StoreInventory inventory;

        public LiHe() : base("liHe")
        {
            inventory = new StoreInventory("cropsMarket");

            name = "liHe";
            className = name;

            facingDir = 1;

            atMap = 8;

            moveAnimSpeed = 10;
            standAnimSpeed = 10;
            moveSpeed = 5;

            spriteSize = new Vector2(64, 128);
            standSize = new Vector2(24, 12);
            spriteFromStandOffset = new Vector2(20, 114);

            spriteSizeAlt = spriteSize;
            spriteFromStandOffsetAlt = spriteFromStandOffset;

            // temps
            standPos = new Vector2(3500, 2600);
            standPos = new Vector2(5445, 1194);

            // anims
            Rectangle frontStandBlink = new Rectangle(0 * (int)spriteSize.X, 0 * (int)spriteSize.Y, (int)spriteSize.X, (int)spriteSize.Y);
            Rectangle frontStand1 = new Rectangle(1 * (int)spriteSize.X, 0 * (int)spriteSize.Y, (int)spriteSize.X, (int)spriteSize.Y);

            Rectangle backStand0 = new Rectangle(0 * (int)spriteSize.X, 2 * (int)spriteSize.Y, (int)spriteSize.X, (int)spriteSize.Y);

            Rectangle sideBlink = new Rectangle(0 * (int)spriteSize.X, 1 * (int)spriteSize.Y, (int)spriteSize.X, (int)spriteSize.Y);
            Rectangle sideStand1 = new Rectangle(1 * (int)spriteSize.X, 1 * (int)spriteSize.Y, (int)spriteSize.X, (int)spriteSize.Y);

            Rectangle frontWalk0 = new Rectangle(1 * (int)spriteSize.X, 0 * (int)spriteSize.Y, (int)spriteSize.X, (int)spriteSize.Y);
            Rectangle frontWalk1 = new Rectangle(2 * (int)spriteSize.X, 0 * (int)spriteSize.Y, (int)spriteSize.X, (int)spriteSize.Y);
            Rectangle frontWalk2 = new Rectangle(3 * (int)spriteSize.X, 0 * (int)spriteSize.Y, (int)spriteSize.X, (int)spriteSize.Y);

            Rectangle backWalk0 = new Rectangle(0 * (int)spriteSize.X, 2 * (int)spriteSize.Y, (int)spriteSize.X, (int)spriteSize.Y);
            Rectangle backWalk1 = new Rectangle(1 * (int)spriteSize.X, 2 * (int)spriteSize.Y, (int)spriteSize.X, (int)spriteSize.Y);
            Rectangle backWalk2 = new Rectangle(2 * (int)spriteSize.X, 2 * (int)spriteSize.Y, (int)spriteSize.X, (int)spriteSize.Y);

            Rectangle sideWalk0 = new Rectangle(1 * (int)spriteSize.X, 1 * (int)spriteSize.Y, (int)spriteSize.X, (int)spriteSize.Y);
            Rectangle sideWalk1 = new Rectangle(2 * (int)spriteSize.X, 1 * (int)spriteSize.Y, (int)spriteSize.X, (int)spriteSize.Y);
            Rectangle sideWalk2 = new Rectangle(3 * (int)spriteSize.X, 1 * (int)spriteSize.Y, (int)spriteSize.X, (int)spriteSize.Y);

            Rectangle sit0 = new Rectangle(0 * (int)spriteSize.X, 3 * (int)spriteSize.Y, (int)spriteSize.X, (int)spriteSize.Y);
            Rectangle sit1 = new Rectangle(1 * (int)spriteSize.X, 3 * (int)spriteSize.Y, (int)spriteSize.X, (int)spriteSize.Y);

            anims = new Animation[9];
            anims[0] = new Animation(name, 10, new Rectangle[] { //
                frontStandBlink ,frontStand1, frontStand1, frontStand1, frontStand1, frontStand1, frontStand1, frontStand1, frontStand1, frontStand1}, standAnimSpeed);
            anims[1] = new Animation(name, 1, new Rectangle[] { //
                backStand0}, 10);
            anims[2] = new Animation(name, 10, new Rectangle[] { //
                sideBlink ,sideStand1, sideStand1, sideStand1, sideStand1, sideStand1, sideStand1, sideStand1, sideStand1, sideStand1}, standAnimSpeed);
            anims[3] = new Animation(name, 10, new Rectangle[] { //
                sideBlink ,sideStand1, sideStand1, sideStand1, sideStand1, sideStand1, sideStand1, sideStand1, sideStand1, sideStand1}, standAnimSpeed);
            anims[3].setHorizontalFlip();

            anims[4] = new Animation(name, 4, new Rectangle[] { //
                frontWalk0 ,frontWalk1, frontWalk0, frontWalk2}, moveAnimSpeed);
            anims[5] = new Animation(name, 4, new Rectangle[] { //
                backWalk0 ,backWalk1, backWalk0, backWalk2}, moveAnimSpeed);
            anims[6] = new Animation(name, 4, new Rectangle[] { //
                sideWalk0 ,sideWalk1, sideWalk0, sideWalk2}, moveAnimSpeed);
            anims[7] = new Animation(name, 4, new Rectangle[] { //
                sideWalk0 ,sideWalk1, sideWalk0, sideWalk2}, moveAnimSpeed);
            anims[7].setHorizontalFlip(); 
            anims[8] = new Animation(name, 10, new Rectangle[] { //
                sit0 , sit1, sit1, sit1, sit1, sit1, sit1, sit1, sit1, sit1}, standAnimSpeed);
        }
        public override void draw(SpriteBatch spriteBatch)
        {
            if (inventory.opened())
            {
                inventory.draw(spriteBatch);
            }
            if (atMap == Player.player.getAtMap())
            {
                float depth = (standPos.Y + standSize.Y / 2) / (Map.Height(atMap) * Map.TILE_HEIGHT);
                anims[currentAnim].draw(spriteBatch, spritePos, 0f, depth);
                if (upperClothing != null) upperClothing.draw(spriteBatch, spritePos, moving, facingDir, anims[currentAnim].getIndex(), depth);
            }
        }

        public override void update(GameTime gameTime)
        {
            // pressed person
            if(!Player.usingWindow && //
                MouseHandler.isClickedL && //
                new Rectangle((int)(spritePos.X - Camera.GetOffset().X), (int)(spritePos.Y - Camera.GetOffset().Y), (int)spriteSize.X, (int)spriteSize.Y).Contains(MouseHandler.Pos)){
                inventory.activate();
            }

            if (inventory.opened())
            {
                inventory.update(gameTime);
            }

            moving = false;

            move();

            if (facingDir == 3 || facingDir == 2)
            {
                spritePos.X = standPos.X - spriteFromStandOffset.X;
                spritePos.Y = standPos.Y - spriteFromStandOffset.Y;
            }
            else if (facingDir == 1 || facingDir == 0)
            {
                spritePos.X = standPos.X - spriteFromStandOffsetAlt.X;
                spritePos.Y = standPos.Y - spriteFromStandOffsetAlt.Y;
            }

            currentAnim = facingDir;
            if (moving) currentAnim += 4;

            anims[currentAnim].update(gameTime);

            // temp sit override
            currentAnim = 8;

            anims[currentAnim].update(gameTime);
        }

        public override void move()
        {
        }

    }
}