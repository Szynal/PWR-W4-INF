
#include "pch.h"
#include <iostream>
#include "Gcd.h"

JNIEXPORT jobject JNICALL Java_GcdJNI_multi01
(JNIEnv *env, jobject obj, jobjectArray a, jobjectArray b) {

	jsize a_len = env->GetArrayLength(a);
	jsize b_len = env->GetArrayLength(b);

	if (a_len == b_len) {

		jclass classDouble = env->FindClass("java/lang/Double");
		jmethodID DoubleValue = env->GetMethodID(classDouble, "doubleValue", "()D");
		jmethodID DoubleInit = env->GetMethodID(classDouble, "<init>", "(D)V");
		jdouble val = 0.0;

		if (DoubleInit == NULL) return NULL;

		for (int i = 0; i < a_len; ++i) {
			jobject a_element = env->GetObjectArrayElement(a, i);
			if (a_element == NULL) return NULL;
			jdouble a_val = env->CallDoubleMethod(a_element, DoubleValue);
			jobject b_element = env->GetObjectArrayElement(b, i);
			if (b_element == NULL) return NULL;
			jdouble b_val = env->CallDoubleMethod(b_element, DoubleValue);
			val += (a_val * b_val);
		}
		return env->NewObject(classDouble, DoubleInit, val);
	}
	else return NULL;
}

JNIEXPORT jobject JNICALL Java_GcdJNI_multi02
(JNIEnv *env, jobject obj, jobjectArray a) {

	jclass thisClass = env->GetObjectClass(obj);
	jfieldID b_ID = env->GetFieldID(thisClass, "b", "[Ljava/lang/Double;");
	jobject b_object = env->GetObjectField(obj, b_ID);
	jobjectArray b = reinterpret_cast<jobjectArray>(b_object);


	jsize a_len = env->GetArrayLength(a);
	jsize b_len = env->GetArrayLength(b);

	if (a_len == b_len) {

		jclass classDouble = env->FindClass("java/lang/Double");
		jmethodID DoubleValue = env->GetMethodID(classDouble, "doubleValue", "()D");
		jmethodID DoubleInit = env->GetMethodID(classDouble, "<init>", "(D)V");
		jdouble val = 0.0;

		if (DoubleInit == NULL) return NULL;

		for (int i = 0; i < a_len; ++i) {
			jobject a_element = env->GetObjectArrayElement(a, i);
			if (a_element == NULL) return NULL;
			jdouble a_val = env->CallDoubleMethod(a_element, DoubleValue);
			jobject b_element = env->GetObjectArrayElement(b, i);
			if (b_element == NULL) return NULL;
			jdouble b_val = env->CallDoubleMethod(b_element, DoubleValue);
			val += (a_val * b_val);
		}
		return env->NewObject(classDouble, DoubleInit, val);
	}
	else return NULL;
}

JNIEXPORT void JNICALL Java_GcdJNI_multi03
(JNIEnv *env, jobject obj) {
}
