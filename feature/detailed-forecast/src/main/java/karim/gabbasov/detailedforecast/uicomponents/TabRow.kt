package karim.gabbasov.detailedforecast.uicomponents

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import karim.gabbasov.designsystem.theme.DarkRed
import karim.gabbasov.designsystem.theme.LightRed
import karim.gabbasov.designsystem.theme.WeatherAppTheme
import karim.gabbasov.ui.util.DayOfWeekNames
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
internal fun TabLayout(
    tabsData: List<Pair<Int, DayOfWeekNames>>,
    pagerState: PagerState
) {
    val scope = rememberCoroutineScope()

    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .background(WeatherAppTheme.colors.forecastDetailsBackground)
            .padding(horizontal = 20.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        itemsIndexed(tabsData) { index, tabData ->
            Tab(
                tabData = tabData,
                pagerState = pagerState,
                scope = scope,
                index = index
            )
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun Tab(
    tabData: Pair<Int, DayOfWeekNames>,
    pagerState: PagerState,
    scope: CoroutineScope,
    index: Int
) {
    Column(
        modifier = Modifier
            .clickable {
                scope.launch {
                    pagerState.animateScrollToPage(index)
                }
            }
            .height(60.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceAround
    ) {
        val backgroundColor = if (pagerState.currentPage == index) {
            Brush.horizontalGradient(listOf(LightRed, DarkRed))
        } else {
            Brush.horizontalGradient(listOf(Color.Transparent, Color.Transparent))
        }
        Text(
            text = stringResource(tabData.second.shortDayOfWeekNameId),
            style = WeatherAppTheme.typography.smallTitle,
            color = WeatherAppTheme.colors.dailyForecastSecondText
        )
        Box(
            modifier = Modifier
                .size(30.dp)
                .clip(CircleShape)
                .background(backgroundColor),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = tabData.first.toString(),
                style = WeatherAppTheme.typography.smallTitle,
                color = WeatherAppTheme.colors.dailyForecastText
            )
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Preview
@Composable
private fun PreviewTabLayout() {
    val tabData = listOf(
        Pair(8, DayOfWeekNames.MONDAY),
        Pair(9, DayOfWeekNames.TUESDAY),
        Pair(10, DayOfWeekNames.WEDNESDAY),
        Pair(11, DayOfWeekNames.THURSDAY),
        Pair(12, DayOfWeekNames.FRIDAY),
        Pair(13, DayOfWeekNames.SATURDAY),
        Pair(14, DayOfWeekNames.SUNDAY)
    )
    TabLayout(
        tabsData = tabData,
        pagerState = rememberPagerState(4)
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Preview
@Composable
private fun PreviewTab() {
    Tab(
        tabData = Pair(12, DayOfWeekNames.FRIDAY),
        pagerState = rememberPagerState(1),
        scope = rememberCoroutineScope(),
        index = 0
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Preview
@Composable
private fun PreviewChosenTab() {
    Tab(
        tabData = Pair(12, DayOfWeekNames.FRIDAY),
        pagerState = rememberPagerState(0),
        scope = rememberCoroutineScope(),
        index = 0
    )
}
