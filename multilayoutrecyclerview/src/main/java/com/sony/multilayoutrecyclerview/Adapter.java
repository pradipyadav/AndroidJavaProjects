package com.sony.multilayoutrecyclerview;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import static com.sony.multilayoutrecyclerview.ModelClass.USER_AD_LAYOUT;
import static com.sony.multilayoutrecyclerview.ModelClass.USER_IMAGE_LAYOUT;
import static com.sony.multilayoutrecyclerview.ModelClass.USER_INFO_LAYOUT;

class Adapter extends RecyclerView.Adapter {
int lastPosition=-1;
    private List<ModelClass> modelClassList;

    public Adapter(List<ModelClass> modelClassList) {
        this.modelClassList = modelClassList;
    }

    @Override
    public int getItemViewType(int position) {

        switch (modelClassList.get(position).getViewType()) {
            case 0:
                return USER_INFO_LAYOUT;
            case 1:
                return USER_AD_LAYOUT;
            case 2:
                return USER_IMAGE_LAYOUT;
            default:
                return -1;
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        switch (viewType) {
            case USER_INFO_LAYOUT:
                View userInfoLayout = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_layout, viewGroup,false);
                return new UserInfoLayout(userInfoLayout);
            case USER_AD_LAYOUT:
                View adLayout = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.ad_layout, viewGroup,false);
                return new AdLayout(adLayout);
            case USER_IMAGE_LAYOUT:
                View imageLayout = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.image_layout, viewGroup,false);
                return new ImageLayout(imageLayout);
            default:
                return null;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
switch (modelClassList.get(position).getViewType()){
    case USER_INFO_LAYOUT:
        int userImage=modelClassList.get(position).getImageResource();
        String title=modelClassList.get(position).getTitle();
        String body=modelClassList.get(position).getBody();
        ((UserInfoLayout)holder).setData(userImage,title,body);
        break;
    case USER_AD_LAYOUT:
        String ad=modelClassList.get(position).getAdText();
        ((AdLayout)holder).setAd(ad);
        break;
    case USER_IMAGE_LAYOUT:
       int resource=modelClassList.get(position).getResource();
        ((ImageLayout)holder).setImageViewge(resource);
        break;
    default:
        return ;
}

if (lastPosition<position){
    Animation animation= AnimationUtils.loadAnimation(holder.itemView.getContext(),android.R.anim.fade_in);
    holder.itemView.setAnimation(animation);
    lastPosition=position;
}
    }

    @Override
    public int getItemCount() {
        return modelClassList.size();
    }

    class UserInfoLayout extends RecyclerView.ViewHolder {
        private ImageView userImage;
        private TextView userName;
        private TextView body;

        public UserInfoLayout(@NonNull View itemView) {
            super(itemView);
            userImage = itemView.findViewById(R.id.imageView);
            userName = itemView.findViewById(R.id.textView);
            body = itemView.findViewById(R.id.textView2);
        }
        private void setData(int image,String name,String bodyText){
            userImage.setImageResource(image);
            userName.setText(name);
            body.setText(bodyText);
        }
    }

    class AdLayout extends RecyclerView.ViewHolder {
        private TextView adText;

        public AdLayout(@NonNull View itemView) {
            super(itemView);
            adText = itemView.findViewById(R.id.txtAd);
        }

        private void setAd(String ad){
            adText.setText(ad);
        }
    }

    class ImageLayout extends RecyclerView.ViewHolder {
        private ImageView imageView;

        public ImageLayout(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.mainImage);
        }

        private void setImageViewge(int image){
            imageView.setImageResource(image);
        }
    }

}
