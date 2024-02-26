package lotto.model

sealed interface ErrorEvent {

    sealed interface PurchaseEvent : ErrorEvent {
        data object InvalidDataType : PurchaseEvent
        data object InvalidPrice : PurchaseEvent
    }

    sealed interface LottoEvent : ErrorEvent {
        data object InvalidDataType : LottoEvent
        data object InvalidNumRange : LottoEvent
        data object InvalidNumCount : LottoEvent
        data object InvalidDuplication : LottoEvent
    }

    sealed interface ManualEvent : ErrorEvent {
        data object InvalidManualCount : ManualEvent
    }

    sealed interface BonusEvent : ErrorEvent {
        data object InvalidDataType : BonusEvent
        data object InvalidNumRange : BonusEvent
        data object InvalidBonusDuplication : BonusEvent
    }
}
