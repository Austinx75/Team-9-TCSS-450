package edu.uw.harmony.UI.Chat.page;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.IntFunction;

import edu.uw.harmony.R;

public class ChatListViewModel extends AndroidViewModel {

    private MutableLiveData<List<ChatPost>> mChatList;
    public ChatListViewModel(@NonNull Application application) {
        super(application);
        mChatList = new MutableLiveData<>();
        mChatList.setValue(new ArrayList<>());
    }
    public void addBlogListObserver(@NonNull LifecycleOwner owner,
                                    @NonNull Observer<? super List<ChatPost>> observer) {
        mChatList.observe(owner, observer);
    }

    private void handleError(final VolleyError error) {
        //you should add much better error handling in a production release.
        //i.e. YOUR PROJECT
        Log.e("CONNECTION ERROR", error.getLocalizedMessage());
    }

    private void handleResult(final JSONObject result) {
        IntFunction<String> getString =
                getApplication().getResources()::getString;
        try {
            mChatList.setValue(new ArrayList<>());
            JSONObject root = result;
            JSONArray chats = root.getJSONArray("chats");
            Log.e("-----------------------", chats.get(0).toString());
            for (int i = 0; i < chats.length(); i++) {
                JSONObject room = chats.getJSONObject(i);

                List<Integer> members = new ArrayList<>();

                ChatPost post = new ChatPost.Builder((int)room.get("chatid"), members, "" + room.get("name")).build();
                Log.e("-----------", mChatList.getValue() + " ");
                boolean contains = false;
                List <ChatPost> list = mChatList.getValue();
                for (ChatPost chat: list) {
                    if ((int)room.get("chatid") == chat.getChatId()) {
                        contains = true;
                    }
                }
                if (!contains) {
                    mChatList.getValue().add(post); }
            }
        } catch (JSONException e){
            e.printStackTrace();
            Log.e("ERROR!", e.getMessage());
        }


//        try {
//            JSONObject root = result;
//            if (root.has(getString.apply(R.string.keys_json_blogs_response))) {
//                JSONObject response =
//                        root.getJSONObject(getString.apply(
//                                R.string.keys_json_blogs_response));
//                if (response.has(getString.apply(R.string.keys_json_blogs_data))) {
//                    JSONArray data = response.getJSONArray(
//                            getString.apply(R.string.keys_json_blogs_data));
//                    for(int i = 0; i < data.length(); i++) {
//                        JSONObject jsonBlog = data.getJSONObject(i);
//                        ChatPost post = new ChatPost.Builder(
//                                jsonBlog.getString(
//                                        getString.apply(
//                                                R.string.keys_json_blogs_pubdate)),
//                                jsonBlog.getString(
//                                        getString.apply(
//                                                R.string.keys_json_blogs_title)))
//                                .addTeaser(jsonBlog.getString(
//                                        getString.apply(
//                                                R.string.keys_json_blogs_teaser)))
//                                .addUrl(jsonBlog.getString(
//                                        getString.apply(
//                                                R.string.keys_json_blogs_url)))
//                                .build();
//                        if (!mChatList.getValue().contains(post)) {
//                            mChatList.getValue().add(post); }
//                    }
//                } else {
//                    Log.e("ERROR!", "No data array");
//                }
//            } else {
//                Log.e("ERROR!", "No response");
//            }
//        } catch (JSONException e) {
//            e.printStackTrace();
//            Log.e("ERROR!", e.getMessage());
//        }
        mChatList.setValue(mChatList.getValue());
    }

    public void connectGet(final String jwt, final String email) {
        String url =
                getApplication().getResources().getString(R.string.base_url) + "chatroom/" + email;
        Log.e("fasjd fkajls dfklja sdjkf alksdfklasjdf", email);
        Request request = new JsonObjectRequest(
                Request.Method.GET,
                url,
                null,
                this::handleResult,
                this::handleError) {
            @Override
            public Map<String, String> getHeaders() {
                Map<String, String> headers = new HashMap<>();
                // add headers <key,value>
                headers.put("Authorization",jwt);
                return headers;
            }
        };
        request.setRetryPolicy(new DefaultRetryPolicy(
                10_000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        //Instantiate the RequestQueue and add the request to the queue
        Volley.newRequestQueue(getApplication().getApplicationContext())
                .add(request);
    }


}
