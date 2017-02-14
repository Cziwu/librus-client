package pl.librus.client.announcements;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.transition.Fade;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import eu.davidea.flexibleadapter.FlexibleAdapter;
import eu.davidea.flexibleadapter.items.IFlexible;
import java8.util.stream.StreamSupport;
import pl.librus.client.R;
import pl.librus.client.datamodel.Announcement;
import pl.librus.client.datamodel.AnnouncementType;
import pl.librus.client.ui.MainActivity;
import pl.librus.client.ui.MainApplication;
import pl.librus.client.ui.MainFragment;

public class AnnouncementsFragment extends MainFragment {

    public AnnouncementsFragment() {
        // Required empty public constructor
    }

    public static AnnouncementsFragment newInstance() {
        Bundle args = new Bundle();
        AnnouncementsFragment fragment = new AnnouncementsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View root = inflater.inflate(R.layout.fragment_announcements, container, false);
        postponeEnterTransition();
        RecyclerView mRecyclerView = (RecyclerView) root.findViewById(R.id.recycler_announcements);

        List<Announcement> announcementList = MainApplication.getData()
                .select(Announcement.class)
                .orderBy(AnnouncementType.START_DATE.desc())
                .get()
                .toList();
        List<AnnouncementItem> announcementItems = StreamSupport.stream(announcementList)
                .map(a -> new AnnouncementItem(a, AnnouncementUtils.getHeaderOf(a, getContext())))
                .collect(java8.util.stream.Collectors.toList());
        Collections.sort(announcementItems);
        List<IFlexible> listItems = new ArrayList<>();
        listItems.addAll(announcementItems);
        final FlexibleAdapter<IFlexible> adapter = new FlexibleAdapter<>(listItems);
        adapter.setDisplayHeadersAtStartUp(true);
        adapter.mItemClickListener = position -> {
            if (adapter.getItem(position) instanceof AnnouncementItem) {

                AnnouncementItem item = (AnnouncementItem) adapter.getItem(position);
                Announcement announcement = item.getAnnouncement();

                FragmentManager fm = getFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();

                AnnouncementDetailsFragment announcementDetailsFragment = AnnouncementDetailsFragment.newInstance(announcement);

                TransitionInflater transitionInflater = TransitionInflater.from(getContext());
                Transition details_enter = transitionInflater.inflateTransition(R.transition.details_enter);
                Transition details_exit = transitionInflater.inflateTransition(R.transition.details_exit);

                setSharedElementEnterTransition(details_enter);
                setSharedElementReturnTransition(details_exit);
                setExitTransition(new Fade());
                announcementDetailsFragment.setSharedElementEnterTransition(details_enter);
                announcementDetailsFragment.setSharedElementReturnTransition(details_exit);

                ft.replace(R.id.content_main, announcementDetailsFragment, "Announcement details transition");
                ft.addSharedElement(item.getBackgroundView(), item.getBackgroundView().getTransitionName());
                ft.commit();
            }
            return false;
        };
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(adapter);
        new Handler().postDelayed(this::startPostponedEnterTransition, 50);
        ((MainActivity) getActivity()).setBackArrow(false);

        return root;
    }

    @Override
    public void setOnSetupCompleteListener(OnSetupCompleteListener listener) {
    }

    @Override
    public void removeListener() {
    }

    @Override
    public int getTitle() {
        return R.string.announcements_view_title;
    }
}
