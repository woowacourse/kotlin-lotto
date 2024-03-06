package model

sealed class LotteryType {
    data object Manual : LotteryType()

    data object Auto : LotteryType()
}
