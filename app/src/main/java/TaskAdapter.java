import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.Task;
import com.mousa.muhammad.muhammadmyfinalproject.MyTask;
import com.mousa.muhammad.muhammadmyfinalproject.R;

public class TaskAdapter extends ArrayAdapter<MyTask> {


    public TaskAdapter(Context context, int resource)
    {
        super(context, resource);
    }

    @androidx.annotation.@NonNull
    @Override
    public View getView(int position, @androidx.annotation.@Nullable View convertView, @androidx.annotation.NonNull ViewGroup parent) {
    // to build  one item from using xml layout
        if (convertView==null)
            convertView=LayoutInflater.from(getContext()).inflate(R.layout.task_item,parent,false);

        MyTask= getItem(position); // return data object number "position"

        TextView tvTitle = convertView.findViewById(R.id.tvitmTitle);
        TextView tvText = convertView.findViewById(R.id.tvitmText);
        SeekBar skbNees = convertView.findViewById(R.id.skbNees);
        SeekBar skpImp = convertView.findViewById(R.id.skbNees);
        ImageButton ibDel = convertView.findViewById(R.id.ibDel);

        // put the data of the object on the view
        tvText.setText(m.getText());
        tvTitle.setText(m.getTitle());

        skpImp.setProgress(m.getImportant());
        skbNees.setProgress(m.getImportant());

        ibDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "Del",Toast.LENGTH_SHORT).show();
            }
        });

        return super.getView(position, convertView, parent);
    }


}
