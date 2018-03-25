package com.example.shahzaib.cloneofgmaildesign;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.content.Context;
import android.view.View;


public class FlipAnimation {

    private View frontIcon, backIcon;
    private boolean isFlip_leftToRight;
    private AnimatorSet leftIn, rightOut, rightIn, leftOut;

    public FlipAnimation(Context context, View frontIcon, View backIcon, boolean isFlip_leftToRight)
    {
        this.frontIcon = frontIcon;
        this.backIcon = backIcon;
        this.isFlip_leftToRight = isFlip_leftToRight;

        leftIn = (AnimatorSet) AnimatorInflater.loadAnimator(context,R.animator.left_in_animation);
        rightOut = (AnimatorSet) AnimatorInflater.loadAnimator(context,R.animator.right_out_animation);
        rightIn = (AnimatorSet) AnimatorInflater.loadAnimator(context,R.animator.right_in_animation);
        leftOut = (AnimatorSet) AnimatorInflater.loadAnimator(context,R.animator.left_out_animation);
    }



    public void performFlip()
    {
        if(isFlip_leftToRight)
        {
            performFlipLeftToRight();
        }
        else
        {
            performFlipRightToLeft();
        }
    }

    private void performFlipLeftToRight()
    {
        frontIcon.setVisibility(View.VISIBLE);
        backIcon.setVisibility(View.VISIBLE);
        backIcon.setAlpha(0);

        leftIn.setTarget(frontIcon);
        rightOut.setTarget(backIcon);

        leftIn.start();
        rightOut.start();
    }

    private void performFlipRightToLeft()
    {
        frontIcon.setVisibility(View.VISIBLE);
        backIcon.setVisibility(View.VISIBLE);
        frontIcon.setAlpha(0);
        rightIn.setTarget(backIcon);
        leftOut.setTarget(frontIcon);
        rightIn.start();
        leftOut.start();
    }

}
