//
//  ResultView.swift
//  Taerimwon
//
//  Created by HanHoon on 5/8/24.
//

import SwiftUI

struct ResultView: View {
    @Binding var username: String
    var body: some View {
        VStack(spacing: 10){
            ScrollView(.vertical, showsIndicators: false){
                VStack {
                    Text("ResultView Page입니다.")
                    Text("username = \(username)")
                }
                
                ForEach(0..<10, content: { index in
                    Text("스크롤뷰 테스트 \(index)").padding()
                        .background(Color.yellow)
                }).padding()
            }
            Button(
                action:{
                    print("Button clicked!")
                }, label: {
                    Text("발주하기")
                        .font(.system(size: 20))
                        .foregroundColor(Color.white)
                        .padding()
                        .background(Color.blue)
                })
        }.padding(.horizontal, 50)
    }
}

#Preview {
    ResultView(username: Binding.constant("Preview username"))
}
