package chapter.android.aweme.ss.com.homework;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.List;

import chapter.android.aweme.ss.com.homework.model.Message;
import chapter.android.aweme.ss.com.homework.widget.CircleImageView;


public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.MessageViewHolder> {
    private static final String TAG = "MessageAdapter";
    private final ListItemClickListener mOnClickListener;
    private static int viewHolderCount;
    List<Message> messages;
    public MessageAdapter(List<Message> messages, ListItemClickListener listener) {
        this.messages = messages;
        mOnClickListener = listener;
        viewHolderCount = 0;
    }
    @NonNull
    @Override
    public MessageViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        Context context = viewGroup.getContext();
        int layoutIdForListItem = R.layout.im_list_item;
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;
        View view = inflater.inflate(layoutIdForListItem, viewGroup, shouldAttachToParentImmediately);
        MessageViewHolder viewHolder = new MessageViewHolder(view);

        Log.d(TAG, "onCreateViewHolder: number of ViewHolders created: " + viewHolderCount);
        viewHolderCount++;
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MessageViewHolder messageViewHolder, int position) {
        Log.d(TAG, "onBindViewHolder: #" + position);
        messageViewHolder.bind(position);
    }

    @Override
    public int getItemCount() {
        return messages.size();
    }

    public class MessageViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private CircleImageView iv_avatar;
        private ImageView robot_notice;
        private TextView tv_title;
        private TextView tv_description;
        private TextView tv_time;
        private int position;
        public MessageViewHolder(@NonNull View itemView) {
            super(itemView);
            iv_avatar = itemView.findViewById(R.id.iv_avatar);
            robot_notice = itemView.findViewById(R.id.robot_notice);
            tv_title = itemView.findViewById(R.id.tv_title);
            tv_description = itemView.findViewById(R.id.tv_description);
            tv_time = itemView.findViewById(R.id.tv_time);
            itemView.setOnClickListener(this);
        }

        public void bind(int position) {
            position = position;
            Message message = messages.get(position);
            /*
            *   private boolean isOfficial;//是否官方
                private String title;//title
                private String time;//时间
                private String description;//描述
                private String icon;//icon
            * */
            tv_title.setText(Integer.toString(position));
            tv_description.setText(message.getDescription());
            tv_time.setText(message.getTime());
            int iconId;
            switch (message.getIcon()){
                case "TYPE_ROBOT":
                    iconId = R.drawable.session_robot;
                    break;
                case "TYPE_GAME":
                    iconId = R.drawable.icon_micro_game_comment;
                    break;
                case "TYPE_SYSTEM":
                    iconId = R.drawable.session_system_notice;
                    break;
                default:
                    iconId = R.drawable.icon_girl;
                    break;
            }
            iv_avatar.setImageResource(iconId);
            if(message.isOfficial()){
                robot_notice.setVisibility(View.VISIBLE);
                robot_notice.setImageResource(R.drawable.im_icon_notice_official);
            }else{
                robot_notice.setVisibility(View.INVISIBLE);
            }
        }

        @Override
        public void onClick(View v) {
            int clickedPosition = getAdapterPosition();
            if (mOnClickListener != null) {
                mOnClickListener.onListItemClick(clickedPosition);
            }
        }
    }

    public interface ListItemClickListener {
        void onListItemClick(int clickedItemIndex);
    }
}
