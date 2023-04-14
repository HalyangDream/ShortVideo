### 模仿抖音上下滑动的Demo
### 1.0.0(2023.4.14)
**1.上下滑动视频<br />**
**2.滑动完成后自动播放当前视频，自动停止上一个视频<br />**
**3.若要运行成功，需要在MainActivity中的bindData()中添加视频链接<br />**
```
 private void bindData() {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add("");
        }
        adapter.addData(list);

        recyclerView.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (recyclerView.getScrollState() == RecyclerView.SCROLL_STATE_IDLE) {
                    autoStopPreVideo();
                    autoPlayCurrentVideo();
                }
            }
        }, 300);
    }
```