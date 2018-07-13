/*
 * Copyright 2016-2018 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
 */

package kotlinx.coroutines.io

import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

internal open class ByteChannelCoroutine(
    parentContext: CoroutineContext,
    open val channel: ByteChannel
) : AbstractCoroutine<Unit>(parentContext, active = true) {
    override fun onCancellation(cause: Throwable?) {
        if (!channel.close(cause) && cause != null)
            handleCoroutineException(context, cause)
    }
}
