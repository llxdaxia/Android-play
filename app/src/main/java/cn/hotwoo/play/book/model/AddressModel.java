package cn.hotwoo.play.book.model;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;

import java.util.ArrayList;
import java.util.List;

import cn.hotwoo.play.book.Address;

/**
 * Created by llxal on 2015/12/8.
 */
public class AddressModel {

    private static final String ADDRESS_URI = "content://com.android.contacts/contacts";
    private List<Address> data = new ArrayList<>();
    private ContentResolver contentResolver;
    private Context mContext;

    public AddressModel(Context context){
        mContext = context;
    }

    public List<Address> getAddressList(){

        //获取一个ContentResolver对象
        contentResolver = mContext.getContentResolver();
        //取得联系人中开始的游标
        Cursor cursor = contentResolver.query(Uri.parse(ADDRESS_URI),null,null,null,null);
        while (cursor.moveToNext()){
            //获取联系人id,姓名
            Address address = new Address();
            List<String> phoneData = new ArrayList<>();
            int id = Integer.parseInt(cursor.getString(cursor.getColumnIndex(android.provider.ContactsContract.Contacts._ID)));
            address.setId(id);
            address.setName(cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME)));
            //获取手机号码,通过SQL语句过滤出对应的id的人的电话号码的数据库那一列,并且返回一个Curse
            Cursor cursorPhone = contentResolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,
                    ContactsContract.CommonDataKinds.Phone.CONTACT_ID + "=" + id, null, null);

            //多个号码的可能
            while (cursorPhone.moveToNext()){
                phoneData.add(cursorPhone.getString(cursorPhone.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)));
            }
            address.setPhone(phoneData);
            data.add(address);
        }
        return data;
    }
}
