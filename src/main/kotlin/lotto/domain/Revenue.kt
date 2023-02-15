package lotto.domain

enum class Revenue(description: String) {
    LOSS("(기준이 1이기 때문에 결과적으로 손해라는 의미임)"),
    NOTHING("(기준이 1이기 때문에 결과적으로 손해도 이득도 아니라는 의미임)"),
    GAIN("(기준이 1이기 때문에 결과적으로 이득이라는 의미임)");

    companion object {
        private const val STANDARD_VALUE = 1

        fun valueOf(profit: Double): Revenue {
            if (profit < STANDARD_VALUE) return LOSS
            if (profit > STANDARD_VALUE) return GAIN
            return NOTHING
        }
    }
}
