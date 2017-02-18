package com.cyclic_order.amarshohor.ListItemActivity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.cyclic_order.amarshohor.ImageActivity.ImageUpload;
import com.cyclic_order.amarshohor.ImageActivity.upload_text;
import com.cyclic_order.amarshohor.R;

/**
 * Created by cyclic_order on 1/31/2016.
 */
public class RowViewHolder extends RecyclerView.ViewHolder {
    ImageView imageView;
    TextView textView;
    Context context;
    public static  int position;


    public RowViewHolder(View view) {
        super(view);
        context = view.getContext();
        this.textView = (TextView) view.findViewById(R.id.title);
        this.imageView = (ImageView) view.findViewById(R.id.image);
        view.setClickable(true);
        view.setOnClickListener(new View.OnClickListener() {
            Intent intent;
            @Override

            public void onClick(View v) {
                position=getPosition();
                intent=new Intent(context,ImageUpload.class);





//                switch(getPosition())
//                {
//                    case 0:
//                        intent=new Intent(context, MapsActivity.class);
//                        break;
//                    case 1:
//                        intent=new Intent(context, UploadToServer.class);
//                        break;
//                    case 2:
//                        intent=new Intent(context, UploadToServer.class);
//                        break;
//                    case 3:
//                        intent=new Intent(context, UploadToServer.class);
//                        break;
//                    case 4:
//                        intent=new Intent(context, UploadToServer.class);
//                        break;
//                    case 5:
//                        intent=new Intent(context, UploadToServer.class);
//                        break;
//                    case 6:
//                        intent=new Intent(context, UploadToServer.class);
//                        break;
//                }
                intent.putExtra("position",position);
                context.startActivity(intent);
            }
        });
    }


}
