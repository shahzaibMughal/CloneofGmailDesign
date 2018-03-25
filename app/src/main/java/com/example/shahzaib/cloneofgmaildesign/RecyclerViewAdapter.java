package com.example.shahzaib.cloneofgmaildesign;

import android.app.Activity;
import android.content.Context;
import android.drm.DrmStore;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v4.content.ContextCompat;
import android.support.v7.view.ActionMode;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.HapticFeedbackConstants;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class RecyclerViewAdapter extends RecyclerView.Adapter<ViewHolder> {


    List<Message> messages;
    Context context;
    RecyclerViewAdapterClickListener listener;
    SparseBooleanArray selectedItems;
    private int selectedItemsCount;
    private int selectedItemIndex, deSelectedItemIndex;
//    List<Message> tempMessages;
    int totalMessagesSize;
    SparseBooleanArray itemsForAnimationAtOnce;




    public RecyclerViewAdapter(Context context,List<Message> messages, RecyclerViewAdapterClickListener listener)
    {
        this.context = context;
        this.messages = messages;
        this.listener = listener;
        selectedItems = new SparseBooleanArray();
        selectedItemsCount = 0;
        selectedItemIndex = -1;
        deSelectedItemIndex = -1;
        totalMessagesSize = messages.size();
        itemsForAnimationAtOnce = new SparseBooleanArray();
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_row,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        Message message = messages.get(position);
        setMessage(
                holder,
                message.getMessageFrom(),
                message.getMessageSubject(),
                message.getMessageBody(),
                message.getMessageTime(),
                message.isRead());



        /* change selected items in Activated state and unSelectedItems in Deactivated state */
        holder.itemView.setActivated(selectedItems.get(position,false));


        // icon animation
        applyAnimation(holder,position);

        applyAnimationAtOnce(holder,position);

        applyStarIcon(holder,position);









        holder.starIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onMessageStarIconClicked(position);
            }
        });

        /** Toggle Seletection
         - item pr long click sy us ko select/deselect(highlight/de-highlight) kr do
         - agr ak item b selected hy to longClick aur onClick both sy items  select/deselect honi chahyain
         **/
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                toggleSelection(position);
//                Toast.makeText(context, "ItemContainer Long Clicked", Toast.LENGTH_SHORT).show();
                return true;
            }
        });
        holder.messageContainer.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                toggleSelection(position);
//                Toast.makeText(context, "MessageContainer Long Clicked", Toast.LENGTH_SHORT).show();
                return true;
            }
        });

        holder.iconContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toggleSelection(position);
            }
        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(selectedItemsCount>0)
                {
                    toggleSelection(position);
                }
            }
        });








        /* - agr ak b item selected nahi hy to jb message pr click ho to read status ko true kr do  aur
          -  agr koi ak item b selected hy to item pr click sy us clicked item ko select/deselect (toggleSelection) krna hy
             aur clicked items k readStatus ko as it is rehnydyna hy jb tk all items deselect na ho jaen.
        */
        holder.messageContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(selectedItemsCount>0)
                {
                    toggleSelection(position);
                }
                else
                {
                    listener.onMessageClick(position);
                }
            }
        });
    }



    @Override
    public int getItemCount() {
        return messages.size();
    }


    private  void setMessage(
            ViewHolder holder,
            String messageFrom,
            String messageSubject,
            String messageBody,
            String messageTime,
            boolean isRead)
    {

        holder.messageFrom.setText(messageFrom);
        holder.messageSubject.setText(messageSubject);
        holder.messageBody.setText(messageBody);
        holder.messageTime.setText(messageTime);

        if(!isRead)
        {
            /* setting the typeFace*/
            holder.messageFrom.setTypeface(null, Typeface.BOLD);
            holder.messageSubject.setTypeface(null,Typeface.BOLD);
            /* setting the color*/
            holder.messageFrom.setTextColor(ContextCompat.getColor(context,R.color.message_from_color_unread));
            holder.messageSubject.setTextColor(ContextCompat.getColor(context,R.color.message_subject_color_unread));
        }
        else
        {
            /* setting the typeFace*/
            holder.messageFrom.setTypeface(null, Typeface.NORMAL);
            holder.messageSubject.setTypeface(null,Typeface.NORMAL);
            /* setting the color*/
            holder.messageFrom.setTextColor(ContextCompat.getColor(context,R.color.message_from_color_read));
            holder.messageSubject.setTextColor(ContextCompat.getColor(context,R.color.message_subject_color_read));
        }

    }



    private void toggleSelection(int position)
    {
        if(selectedItems.get(position,false))
        {
            selectedItems.delete(position);
            selectedItemsCount--;
            deSelectedItemIndex = position;
            listener.onMessageItemDeSelected(position,selectedItemsCount);
//            animationItemsIndex.delete(position);
            notifyItemChanged(position);
        }
        else
        {
            selectedItems.put(position,true);
            selectedItemsCount++;
            selectedItemIndex = position;
            listener.onMessageItemSelected(position,selectedItemsCount);
//            animationItemsIndex.put(position, true);
            notifyItemChanged(position);
        }
    }


    private void applyStarIcon(ViewHolder holder, int position) {
        if(messages.get(position).isStared())
        {
            holder.starIcon.setImageResource(R.drawable.ic_star_filled);
            holder.starIcon.setColorFilter(ContextCompat.getColor(context, R.color.icon_tint_selected));
        }
        else
        {
            holder.starIcon.setImageResource(R.drawable.ic_star_empty);
            holder.starIcon.setColorFilter(ContextCompat.getColor(context, R.color.icon_tint_normal));
        }
    }

//     flip the currently selected/deselected item.
//    *  aur selected items ki profile pic back wali rakhni hy aur deselected item ki profile pic front wali
    private void applyAnimation(ViewHolder holder, int position)
    {
        if(selectedItems.get(position,false))
        {
            holder.frontIcon.setVisibility(View.GONE);
            resetIconYAxis(holder.backIcon);
            holder.backIcon.setVisibility(View.VISIBLE);

            // perform flip animation if on currently selected item
            if(selectedItemIndex == position)
            {
                new FlipAnimation(context,holder.frontIcon,holder.backIcon,true).performFlip();
                resetSelectedItemIndex();
            }

        }
        else
        {
            if(deSelectedItemIndex == position)
            {
                holder.backIcon.setVisibility(View.VISIBLE);
                new FlipAnimation(context,holder.frontIcon,holder.backIcon,false).performFlip();
                resetDeSelectedItemIndex();
            }
            else
            {
                holder.backIcon.setVisibility(View.GONE);
                resetIconYAxis(holder.frontIcon);
                holder.frontIcon.setVisibility(View.VISIBLE);
            }
        }
    }

    private void resetIconYAxis(View view) {
        if (view.getRotationY() != 0) {
            view.setRotationY(0);
        }
    }

    private void resetSelectedItemIndex()
    {
        selectedItemIndex = -1;
    }
    private void resetDeSelectedItemIndex(){deSelectedItemIndex = -1;}


    private void applyAnimationAtOnce(ViewHolder holder, int position)
    {
        /* How i do it:
        *  jo items ak sath deselect hoi hain un k indexes ko store kr liya hy aur
        *  list ko change k liye notify krny k bad matlb jb sub selected item deselected state main aagai hain
        *  to just, jo indexes save kiye hain un items pr animation perform kr di hy
        *
        *  note: jo items animate hoti jati hain k index ko list main sy delete b krty jana hy
        * */
        if(itemsForAnimationAtOnce.get(position,false))
        {
            holder.backIcon.setVisibility(View.VISIBLE);
            new FlipAnimation(context,holder.frontIcon,holder.backIcon,false).performFlip();
            itemsForAnimationAtOnce.delete(position);
        }
    }

    public  void updataMessages(List<Message> newMessages)
    {
        int oldMessagesSize = this.messages.size();
        int newMessagesSize = newMessages.size();
        this.messages = newMessages;

        Toast.makeText(context, ""+(oldMessagesSize-newMessagesSize)+" Deleted", Toast.LENGTH_SHORT).show();
    }


    public void resetList()
    {
        selectedItems.clear();
        selectedItemsCount = 0;
        notifyDataSetChanged();
    }



    // deselect agr krna hy to animaiton b perform krni hy
    public void deselectAllselectedItems()
    {
        selectedItems.clear();
        selectedItemsCount = 0;
    }


    public void setItemsForAnimationAtOnce(SparseBooleanArray items)
    {// get the items , jin ko ak sath animate krna hy
        this.itemsForAnimationAtOnce = items;
    }


    interface RecyclerViewAdapterClickListener
    {
        void onMessageClick(int position);
        void onMessageItemSelected(int position, int selectedItemCount);
        void onMessageItemDeSelected(int position,int selectedItemsCount);
        void onMessageStarIconClicked(int position);
    }

}
