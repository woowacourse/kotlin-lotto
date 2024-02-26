package lotto.model

sealed interface UserEvent {

    sealed interface PurchaseEvent : UserEvent {
        data class Success(val price : Int) : PurchaseEvent
        data object InvalidDataType : PurchaseEvent
        data object InvalidPrice : PurchaseEvent
        data object UnknownError : PurchaseEvent
    }

    sealed interface LottoEvent : UserEvent {
        data class Success(val lotto: Lotto): LottoEvent
        data object InvalidDataType : LottoEvent
        data object InvalidNumRange : LottoEvent
        data object InvalidNumCount : LottoEvent
        data object UnknownError : LottoEvent
    }

    sealed interface ManualEvent : UserEvent {
        data class Success(val count: Int): ManualEvent
        data object InvalidDataType : ManualEvent
        data object InvalidManualCount : ManualEvent
        data object UnknownError : ManualEvent
    }

    sealed interface BonusEvent : UserEvent {
        data class Success(val bonusNumber: BonusNumber): BonusEvent
        data object InvalidDataType : BonusEvent
        data object InvalidNumRange : BonusEvent
        data object InvalidBonusDuplication : BonusEvent
        data object UnknownError : BonusEvent
    }
}
