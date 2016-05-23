/*
 * This file is auto-generated.  DO NOT MODIFY.
 * Original file: F:\\anzhuoxuexi\\37_֧����\\src\\com\\example\\alipay\\IAlipayService.aidl
 */
package com.example.alipay;
public interface IAlipayService extends android.os.IInterface
{
/** Local-side IPC implementation stub class. */
public static abstract class Stub extends android.os.Binder implements com.example.alipay.IAlipayService
{
private static final java.lang.String DESCRIPTOR = "com.example.alipay.IAlipayService";
/** Construct the stub at attach it to the interface. */
public Stub()
{
this.attachInterface(this, DESCRIPTOR);
}
/**
 * Cast an IBinder object into an com.example.alipay.IAlipayService interface,
 * generating a proxy if needed.
 */
public static com.example.alipay.IAlipayService asInterface(android.os.IBinder obj)
{
if ((obj==null)) {
return null;
}
android.os.IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
if (((iin!=null)&&(iin instanceof com.example.alipay.IAlipayService))) {
return ((com.example.alipay.IAlipayService)iin);
}
return new com.example.alipay.IAlipayService.Stub.Proxy(obj);
}
@Override public android.os.IBinder asBinder()
{
return this;
}
@Override public boolean onTransact(int code, android.os.Parcel data, android.os.Parcel reply, int flags) throws android.os.RemoteException
{
switch (code)
{
case INTERFACE_TRANSACTION:
{
reply.writeString(DESCRIPTOR);
return true;
}
case TRANSACTION_callPayInService:
{
data.enforceInterface(DESCRIPTOR);
java.lang.String _arg0;
_arg0 = data.readString();
float _arg1;
_arg1 = data.readFloat();
int _result = this.callPayInService(_arg0, _arg1);
reply.writeNoException();
reply.writeInt(_result);
return true;
}
}
return super.onTransact(code, data, reply, flags);
}
private static class Proxy implements com.example.alipay.IAlipayService
{
private android.os.IBinder mRemote;
Proxy(android.os.IBinder remote)
{
mRemote = remote;
}
@Override public android.os.IBinder asBinder()
{
return mRemote;
}
public java.lang.String getInterfaceDescriptor()
{
return DESCRIPTOR;
}
@Override public int callPayInService(java.lang.String account, float money) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
int _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeString(account);
_data.writeFloat(money);
mRemote.transact(Stub.TRANSACTION_callPayInService, _data, _reply, 0);
_reply.readException();
_result = _reply.readInt();
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
}
static final int TRANSACTION_callPayInService = (android.os.IBinder.FIRST_CALL_TRANSACTION + 0);
}
public int callPayInService(java.lang.String account, float money) throws android.os.RemoteException;
}
