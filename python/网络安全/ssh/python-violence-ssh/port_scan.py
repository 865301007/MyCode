#!/usr/bin/env python
# -*- coding: utf-8 -*-

import socket,threading,os

class ScanThread(threading.Thread):
    def __init__(self, ip, startport, endport, timeout, fpath):
        threading.Thread.__init__(self)
        self.ip = ip
        self.startport = startport
        self.endport = endport
        self.timeout = timeout
        self.fpath = fpath
        self.fp = open(fpath, "a")

    def run(self):
        for port in range(self.startport, self.endport+1):
            print("Now scan server %s port %d \n " % (self.ip, port))
            sk = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
            sk.settimeout(self.timeout)
            try:
                sk.connect((self.ip, port))
                self.fp.write('Server %s port %d OK!\n' % (self.ip, port))
            except Exception:
                pass
            sk.close()

def main():
    ip=raw_input('请输入目标主机：(默认:127.0.0.1)')
    if ip=='':
        ip='127.0.0.1'
    s=raw_input('请输入目标主机开始端口：(默认:8080)')
    if s=='':
        startport=8080
    else:
        startport=int(s)

    s=raw_input('请输入目标主机结束端口：(默认:8090)')
    if s=='':
        endport=8090
    else:
        endport=int(s)

    scan = ScanThread(ip, startport, endport, 2, "log/"+ip)
    scan.start()

if __name__ == '__main__':
    main()