package com.byankina.bibaboba;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import java.util.Arrays;
import java.util.List;

public class SecondFragment extends Fragment {

    private ExpandableListView expandableListView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_second, container, false);

        expandableListView = view.findViewById(R.id.expandableListView);

        List<String> gameNames = Arrays.asList(
                "Sonic the Hedgehog (1991)",
                "Sonic the Hedgehog 2",
                "Sonic the Hedgehog 3",
                "Sonic & Knuckles",
                "Sonic Adventure",
                "Sonic Adventure 2",
                "Sonic Heroes",
                "Sonic Generations",
                "Sonic Mania",
                "Sonic Frontiers"
        );

        List<String> gameDescriptions = Arrays.asList(
                "Революционный платформер, представивший миру синего ежа Соника. Игра отличается высокоскоростным геймплеем, где игроки собирают кольца и сражаются с роботами!",
                "Знаковое продолжение с появлением лисёнка Тейлса в качестве напарника. Игра добавила знаменитый спин-даш для разгона с места!",
                "Масштабное продолжение с новым персонажем – ехидной Наклзом. Добавлена система сохранения прогресса и новые типы щитов!",
                "Прямое продолжение Sonic 3 с инновационной технологией 'lock-on'. Наклз становится играбельным персонажем!",
                "Первое полноценное 3D-приключение Соника с открытым миром-хабом и шестью играбельными персонажами!",
                "Динамичный экшен с двумя параллельными историями героев и злодеев. Представлены новые персонажи: Shadow the Hedgehog!",
                "Командный экшен-платформер, где игрок управляет тройкой персонажей с разными способностями!",
                "Юбилейное издание, объединяющее классический 2D и современный 3D геймплей!",
                "Созданная фанатами серии игра, возвращающая классический 2D геймплей. Сочетает переосмысленные зоны из старых игр!",
                "Первая игра серии в формате открытого мира. Исследование таинственных Старфолл Islands!"
        );

        GameDescriptionAdapter adapter = new GameDescriptionAdapter(getContext(), gameNames, gameDescriptions);
        expandableListView.setAdapter(adapter);

        return view;
    }
}