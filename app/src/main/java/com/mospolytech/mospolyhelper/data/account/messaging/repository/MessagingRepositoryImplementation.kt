package com.mospolytech.mospolyhelper.data.account.messaging.repository

import com.mospolytech.mospolyhelper.data.account.auth.local.AuthJwtLocalDataSource
import com.mospolytech.mospolyhelper.data.account.messaging.local.MessagingLocalDataSource
import com.mospolytech.mospolyhelper.data.account.messaging.remote.MessagingRemoteDataSource
import com.mospolytech.mospolyhelper.data.core.local.SharedPreferencesDataSource
import com.mospolytech.mospolyhelper.domain.account.messaging.model.Message
import com.mospolytech.mospolyhelper.domain.account.messaging.repository.MessagingRepository
import com.mospolytech.mospolyhelper.utils.*
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class MessagingRepositoryImplementation(
    private val remoteDataSource: MessagingRemoteDataSource,
    private val localDataSource: MessagingLocalDataSource,
    private val prefDataSource: SharedPreferencesDataSource,
    private val jwtLocalDataSource: AuthJwtLocalDataSource
): MessagingRepository {

    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO

    @Suppress("UNCHECKED_CAST")
    override suspend fun getDialog(dialogKey: String): Flow<Result<List<Message>>> = flow {
        val sessionId = prefDataSource.get(
            PreferenceKeys.SessionId,
            PreferenceDefaults.SessionId
        )
        val res = remoteDataSource.getMessages(sessionId, dialogKey)
        res.onSuccess {
            localDataSource.setDialog(it, dialogKey)
        }
        emit(res)
    }.flowOn(ioDispatcher)

    override suspend fun getLocalDialog(dialogKey: String): Flow<Result<List<Message>>>{
        val dialog = localDataSource.getJson(dialogKey)
        return flow {
            if (dialog.isNotEmpty()) emit(localDataSource.getDialog(dialog))
        }.flowOn(ioDispatcher)
    }

    override suspend fun sendMessage(dialogKey: String, message: String, fileNames: List<String>): Flow<Result<List<Message>>> = flow {
        val sessionId = prefDataSource.get(
            PreferenceKeys.SessionId,
            PreferenceDefaults.SessionId
        )
        val res = remoteDataSource.sendMessage(sessionId, dialogKey, message, fileNames)
        res.onSuccess {
            localDataSource.setDialog(it, dialogKey)
        }
        emit(res)
    }.flowOn(ioDispatcher)

    override fun getName() = jwtLocalDataSource.get()?.getName() ?: ""

    override fun getAvatar() = jwtLocalDataSource.get()?.getAvatar() ?: ""

}