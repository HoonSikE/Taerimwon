//
//  ContentView.swift
//  Taerimwon
//
//  Created by HanHoon on 5/8/24.
//

import SwiftUI

struct ContentView: View {
    var body: some View {
        OrderView()
    }
}

// 주문하기 화면
struct OrderView: View {
    @State private var username: String = ""
    @State private var password: String = ""
    
    var body: some View {
        // 네비게이션뷰로 감싼다.
        NavigationView {
            VStack(spacing: 10){
                ScrollView(.vertical, showsIndicators: false){
                    HStack{
                        TextField("사용자 이름", text: $username)
                            .autocapitalization(.none)
                            .autocorrectionDisabled(true)
                            .textFieldStyle(RoundedBorderTextFieldStyle())
                        Button(action: {
                            self.username = ""
                        }){
                            if(self.username.count > 0){
                                Image(systemName: "multiply.circle.fill")
                                    .font(.system(size: 25))
                                    .foregroundColor(.secondary)
                            }
                        }
                    }
                    HStack{
                        SecureField("비밀번호", text: $password)
                            .autocapitalization(.none)
                            .autocorrectionDisabled(true)
                            .textFieldStyle(RoundedBorderTextFieldStyle())
                        Button(action: {
                            self.password = ""
                        }){
                            if(self.password.count > 0){
                                Image(systemName: "multiply.circle.fill")
                                    .font(.system(size: 25))
                                    .foregroundColor(.secondary)
                            }
                        }
                    }
                    
                    Text("입력한 비번 : \(password)")
                    
                    ForEach(0..<10, content: { index in
                        Text("스크롤뷰 테스트 \(index)").padding()
                            .background(Color.yellow)
                    }).padding()
                }
                NavigationLink(destination: ResultView(username: $username)) {
                    Text("주문하기")
                }
                .font(.system(size: 20))
                .frame(width: 350, height: 20)
                .buttonStyle(PlainButtonStyle())
                .foregroundColor(Color.white)
                .padding()
                .background(Color.blue)
                .cornerRadius(10)
            }.padding(.horizontal, 50)
        }
    }
}


#Preview {
    ContentView()
}
