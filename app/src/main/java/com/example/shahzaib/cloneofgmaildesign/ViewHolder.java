package com.example.shahzaib.cloneofgmaildesign;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by Shahzaib on 3/23/2018.
 */

public class ViewHolder extends RecyclerView.ViewHolder {

    RelativeLayout iconContainer,frontIcon,backIcon;
    ImageView frontIconImage;
    TextView frontIconText;
    LinearLayout messageContainer;
    TextView messageFrom, messageSubject, messageBody, messageTime;
    ImageView starIcon;

    public ViewHolder(View itemView) {
        super(itemView);
        iconContainer = itemView.findViewById(R.id.iconContainer);
        frontIcon = itemView.findViewById(R.id.frontIcon);
        backIcon = itemView.findViewById(R.id.backIcon);
        frontIconImage = itemView.findViewById(R.id.frontIconImage);
        frontIconText = itemView.findViewById(R.id.frontIconText);
        messageContainer = itemView.findViewById(R.id.messageContainer);
        messageFrom = itemView.findViewById(R.id.messagefrom);
        messageSubject = itemView.findViewById(R.id.messageSubject);
        messageBody = itemView.findViewById(R.id.messageBody);
        messageTime = itemView.findViewById(R.id.messageTime);
        starIcon  = itemView.findViewById(R.id.starIcon);
    }
}
