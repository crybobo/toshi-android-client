package com.tokenbrowser.view.adapter;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tokenbrowser.model.local.Contact;
import com.tokenbrowser.model.local.User;
import com.tokenbrowser.token.R;
import com.tokenbrowser.view.adapter.listeners.OnItemClickListener;
import com.tokenbrowser.view.adapter.listeners.OnUpdateListener;
import com.tokenbrowser.view.adapter.viewholder.ClickableViewHolder;
import com.tokenbrowser.view.adapter.viewholder.ContactViewHolder;

import java.util.ArrayList;
import java.util.List;

public class ContactsAdapter extends RecyclerView.Adapter<ContactViewHolder> implements ClickableViewHolder.OnClickListener {

    private List<User> users;
    private OnItemClickListener<User> onItemClickListener;
    private OnUpdateListener onUpdateListener;

    public ContactsAdapter() {
        this.users = new ArrayList<>(0);
    }

    public void mapContactsToUsers(final List<Contact> contacts) {
        this.users = new ArrayList<>(contacts.size());
        for (final Contact contact : contacts) {
            this.users.add(contact.getUser());
        }
        notifyAdapterChanged();
    }

    public ContactsAdapter setUsers(final List<User> users) {
        this.users = users;
        notifyAdapterChanged();
        return this;
    }

    @Override
    public ContactViewHolder onCreateViewHolder(final ViewGroup parent, int viewType) {
        final View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item__contact, parent, false);
        return new ContactViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final ContactViewHolder holder, final int position) {
        final User user = this.users.get(position);
        holder.setUser(user);
        holder.setOnClickListener(this);
    }

    @Override
    public int getItemCount() {
        return this.users.size();
    }

    @Override
    public void onClick(final int position) {
        if (this.onItemClickListener == null) {
            return;
        }

        final User clickedUser = users.get(position);
        this.onItemClickListener.onItemClick(clickedUser);
    }

    public ContactsAdapter setOnItemClickListener(final OnItemClickListener<User> onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
        return this;
    }

    public ContactsAdapter setOnUpdateListener(final OnUpdateListener onUpdateListener) {
        this.onUpdateListener = onUpdateListener;
        return this;
    }

    public void clear() {
        this.users.clear();
        notifyAdapterChanged();
    }

    private void notifyAdapterChanged() {
        notifyDataSetChanged();
        notifyListeners();
    }

    private void notifyListeners() {
        if (this.onUpdateListener != null) {
            this.onUpdateListener.onUpdate();
        }
    }
}